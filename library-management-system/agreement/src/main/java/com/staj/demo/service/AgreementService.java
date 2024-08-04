package com.staj.demo.service;
import com.staj.demo.dto.AgreementDto;
import com.staj.demo.exception.AgreementNotFoundException;
import com.staj.demo.model.Agreement;
import com.staj.demo.model.Book;
import com.staj.demo.repository.AgreementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@AllArgsConstructor
@Service
public class AgreementService {
    private AgreementRepository agreementRepository;
    private WebClient webClient;
    // Kitap ödünç alma işlemi
    public void borrowBook(AgreementDto agreementDto) {
        Agreement agreement=new Agreement();
        Book book = new Book();
        agreement.setBook(agreementDto.getBook());
        agreement.setUser(agreementDto.getUser());
        
        try {
             book = webClient.get()
                    .uri("http://localhost:8086/api/books/get/"+agreementDto.getBook().getId())
                    .retrieve()
                    .bodyToMono(Book.class)
                    .block();
        } catch (Exception e) {
            throw new IllegalStateException("Book not found.");
        }


        if (!book.getAvailability()) {
            throw new IllegalStateException("Book is not available.");
        }
        else{
            // Ödünç alma tarihini bugünün tarihi olarak ayarla
            agreement.setBorrowDate(new Date());
            book.setQuantity(book.getQuantity()-1);
            if(book.getQuantity()==0){
                book.setAvailability(false);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 15);
            Date returnDate = calendar.getTime();
            agreement.setReturnDate(returnDate);
            agreement.setStatus("Borrowed");
            agreementRepository.save(agreement);

            webClient.put()
                    .uri("http://localhost:8086/api/books/update-book/"+book.getId())
                    .bodyValue(book)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            // Return date'i bugünden 15 gün sonrasına ayarla

        }
    }

    // Ödünç süresini uzatma işlemi
    public void extendBorrowDate(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid agreement Id:" + agreementId));

        if (agreement.getStatus().equals("Returned")) {
            throw new IllegalStateException("Cannot extend return date for a book that has already been returned.");
        }
        // Return date'i 15 gün ekleyerek uzat
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(agreement.getReturnDate());
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        Date newReturnDate = calendar.getTime();
        agreement.setReturnDate(newReturnDate);
        agreement.setStatus("Extended");
        agreementRepository.save(agreement);
    }
    // Kitap iade işlemi
    public void returnBook(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid agreement Id:" + agreementId));

        if (agreement.getStatus().equals("Returned")) {
            throw new IllegalStateException("Book has already been returned.");
        }

        agreement.setStatus("Returned");
        agreementRepository.save(agreement);
    }
    // Agreement silme işlemi
    public void deleteAgreement(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new AgreementNotFoundException("Agreement not found with id: " + agreementId));

        agreementRepository.delete(agreement);
    }
    public List<Agreement> getAllAgreements() {
        return agreementRepository.findAll();
    }
}
