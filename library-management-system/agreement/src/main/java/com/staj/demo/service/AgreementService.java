package com.staj.demo.service;
import com.staj.demo.dto.AgreementDto;
import com.staj.demo.exception.AgreementNotFoundException;
import com.staj.demo.model.Agreement;
import com.staj.demo.repository.AgreementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AgreementService {
    private AgreementRepository agreementRepository;


    // Kitap ödünç alma işlemi
    public void borrowBook(AgreementDto agreementDto) {
        Agreement agreement=new Agreement();
        agreement.setBook(agreementDto.getBook());
        agreement.setUser(agreementDto.getUser());

        /*
    //user check
        User user = userRepository.getUserById(agreement.getUser().getId());
        if (user == null) {
            throw new IllegalArgumentException("User with id " + agreement.getUser().getId() + " does not exist.");
        }
        if(isBookBorrowed(agreement.getBook().getId())) {
            throw new IllegalStateException("Book is already borrowed.");
        }
*/

        // Ödünç alma tarihini bugünün tarihi olarak ayarla
        agreement.setBorrowDate(new Date());
        // Return date'i bugünden 15 gün sonrasına ayarla
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        Date returnDate = calendar.getTime();
        agreement.setReturnDate(returnDate);

        agreement.setStatus("Borrowed");

        agreementRepository.save(agreement);
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
    // Kitabın ödünç alınıp alınmadığını kontrol eden fonksiyon
    public Boolean isBookBorrowed(Long bookId) {
        List<Agreement> agreements = agreementRepository.findAll();
        for (Agreement agreement : agreements) {
            if (agreement.getBook().getId().equals(bookId) && !agreement.getStatus().equals("Returned")) {
                return true;
            }
        }
        return false;
    }


    public List<Agreement> getAllAgreements() {
        return agreementRepository.findAll();
    }
}
