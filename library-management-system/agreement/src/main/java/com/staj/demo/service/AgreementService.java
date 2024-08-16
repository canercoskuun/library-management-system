package com.staj.demo.service;
import com.staj.demo.dto.AgreementDto;
import com.staj.demo.enums.StatusType;
import com.staj.demo.exception.AgreementNotFoundException;
import com.staj.demo.model.Agreement;
import com.staj.demo.model.Book;
import com.staj.demo.repository.AgreementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;

@Slf4j
@Service
public class AgreementService {
    private final AgreementRepository agreementRepository;
    private final WebClient webClient;

    public AgreementService(AgreementRepository agreementRepository, WebClient webClient) {
        this.agreementRepository = agreementRepository;
        this.webClient = webClient;
    }

    // Kitap ödünç alma işlemi
    public Agreement borrowBook(AgreementDto agreementDto) {
        Agreement agreement = new Agreement();
        Book book;
        agreement.setBook(agreementDto.getBook());
        agreement.setUser(agreementDto.getUser());

        try {
            // Kitap bilgilerini al
            book = webClient.get()
                    .uri("http://localhost:8086/api/books/get/" + agreementDto.getBook().getId())
                    .retrieve()
                    .bodyToMono(Book.class)
                    .block();
            // Böyle bir kitap mevcut değilse hata fırlat
            if (book == null) {
                log.warn("Book not found");
                throw new IllegalStateException("Book not found.");
            }


            // Kitap stokta yoksa hata fırlat
            if (!book.getAvailability()) {
                log.warn("Book is not available");
                throw new IllegalStateException("Book is not available.");
            }

            // Ödünç alma tarihini bugünün tarihi olarak ayarla
            agreement.setBorrowDate(new Date());

            // Kitap miktarını güncelle
            book.setQuantity(book.getQuantity() - 1);
            if (book.getQuantity() == 0) {
                //Tüm kitaplar ödünç alındıysa kitabın mevcudiyetini false yap
                book.setAvailability(false);
            }

            // Return tarihini ayarla
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 15);
            Date returnDate = calendar.getTime();
            agreement.setReturnDate(returnDate);

            // Ödünç alınan kitabın durumunu BORROWED yap
            agreement.setStatus(StatusType.BORROWED);
            // Agreement nesnesini kaydet
            agreementRepository.save(agreement);

            // Kitap bilgilerini güncelle
            webClient.put()
                    .uri("http://localhost:8086/api/books/update-book/" + book.getId())
                    .headers(headers -> headers.setBasicAuth("admin", "admin"))
                    .bodyValue(book)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .doOnError(e -> System.err.println("Hata: " + e.getMessage()))
                    .block();
            log.info("Book borrowed successfully");
            return agreement;

        } catch (Exception e) {
            // Genel hata yönetimi
            throw new IllegalStateException("An error occurred while borrowing the book: " + e.getMessage(), e);
        }
    }

    // Ödünç süresini uzatma işlemi
    public Agreement extendBorrowDate(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid agreement Id:" + agreementId));

        if (agreement.getStatus().equals(StatusType.RETURNED)) {
            throw new IllegalStateException("Cannot extend return date for a book that has already been returned.");
        }
        // Return date'i 15 gün ekleyerek uzat
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(agreement.getReturnDate());
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        Date newReturnDate = calendar.getTime();
        agreement.setReturnDate(newReturnDate);
        agreement.setStatus(StatusType.EXTENDED);
        agreementRepository.save(agreement);
        log.info("Borrow date extended successfully");
        return agreement;
    }
    // Kitap iade işlemi
    public Agreement returnBook(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid agreement Id:" + agreementId));

        if (agreement.getStatus().equals(StatusType.RETURNED)) {
            throw new IllegalStateException("Book has already been returned.");
        }
        Book book= webClient.get()
                .uri("http://localhost:8086/api/books/get/"+agreement.getBook().getId())
                .retrieve()
                .bodyToMono(Book.class)
                .block();
        book.setQuantity(book.getQuantity()+1);
        if(book.getQuantity()>0){
            book.setAvailability(true);
        }


        webClient.put()
                .uri("http://localhost:8086/api/books/update-book/" + book.getId())
                .headers(headers -> headers.setBasicAuth("admin", "admin"))
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(e -> System.err.println("Hata: " + e.getMessage()))
                .block();


        // Kitap iade tarihini bugünün tarihi olarak ayarla
        agreement.setReturnDate(new Date());
        agreement.setStatus(StatusType.RETURNED);
        agreementRepository.save(agreement);
        log.info("Book returned successfully");
        return agreement;
    }
    // Agreement silme işlemi
    public void deleteAgreement(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new AgreementNotFoundException("Agreement not found with id: " + agreementId));
        log.info("Agreement deleted successfully");
        agreementRepository.delete(agreement);
    }
    public List<Agreement> getAllAgreements() {
        return agreementRepository.findAll();
    }

    public List<Agreement> getAgreementsByUserId(Long userId) {
        return agreementRepository.findByUserId(userId);
    }
}
