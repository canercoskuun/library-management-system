package com.staj.demo.controller;
import com.staj.demo.dto.AgreementDto;
import com.staj.demo.service.AgreementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agreements")
public class AgreementController {

    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping("user/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody AgreementDto agreementDto) {
        try {
          return new ResponseEntity<>(agreementService.borrowBook(agreementDto), HttpStatus.CREATED);
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
    @PutMapping("user/{agreementId}/extend")
    public ResponseEntity<?> extendBorrowDate(@PathVariable Long agreementId) {
        try {
            return new ResponseEntity<>(agreementService.extendBorrowDate(agreementId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Kitap iade endpoint'i
    @PutMapping("user/{agreementId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long agreementId) {
        try {
            return new ResponseEntity<>(agreementService.returnBook(agreementId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Agreement silme işlemi
    @DeleteMapping("admin/{id}")
    public ResponseEntity<?> deleteAgreement(@PathVariable(name = "id") Long id) {
        try{
            agreementService.deleteAgreement(id);
            return ResponseEntity.ok("Agreement deleted successfully");
        }
       catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    //Tüm anlaşmaları getiren endpoint
    @GetMapping("admin/all")
    public ResponseEntity<?> getAllAgreements() {
        try {
            return new ResponseEntity<>(agreementService.getAllAgreements(),HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Bir userin tüm anlaşmalarını getiren endpoint
    @GetMapping("admin/user/{userId}")
    public ResponseEntity<?> getAgreementsByUserId(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(agreementService.getAgreementsByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
