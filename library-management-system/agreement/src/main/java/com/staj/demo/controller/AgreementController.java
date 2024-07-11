package com.staj.demo.controller;
import com.staj.demo.dto.AgreementDto;
import com.staj.demo.model.Agreement;
import com.staj.demo.service.AgreementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/agreements")
public class AgreementController {
    private  AgreementService agreementService;
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody AgreementDto agreementDto) {
        try {
            agreementService.borrowBook(agreementDto);
            return ResponseEntity.ok("Book borrowed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    /*
    {
  "user": {
    "id": 1
  },
  "book": {
    "id": 12365
  }
}
     */
    // Ödünç süresini uzatma işlemi için PUT endpoint'i
    @PutMapping("/{agreementId}/extend")
    public ResponseEntity<?> extendBorrowDate(@PathVariable Long agreementId) {
        try {
            agreementService.extendBorrowDate(agreementId);
            return ResponseEntity.ok("Borrow date extended successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Kitap iade endpoint'i
    @PutMapping("/{agreementId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long agreementId) {
        try {
            agreementService.returnBook(agreementId);
            return ResponseEntity.ok("Book returned successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Agreement silme işlemi
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgreement(@PathVariable(name = "id") Long id) {
        try{
            agreementService.deleteAgreement(id);
            return ResponseEntity.ok("Agreement deleted successfully");
        }
       catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
    //Kitabın ödünç alınıp alınmadığını kontrol eden endpoint
    @GetMapping("/books/{bookId}/isBorrowed")
    public ResponseEntity<?> isBookBorrowed(@PathVariable Long bookId) {
        try {
            Boolean isBorrowed = agreementService.isBookBorrowed(bookId);
            return ResponseEntity.ok(isBorrowed);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Tüm anlaşmaları getiren endpoint
    @GetMapping("/all")
    public ResponseEntity<?> getAllAgreements() {
        try {
            List<Agreement> agreements = agreementService.getAllAgreements();
            return ResponseEntity.ok(agreements);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
