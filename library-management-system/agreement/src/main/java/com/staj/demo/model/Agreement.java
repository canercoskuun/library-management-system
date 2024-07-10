package com.staj.demo.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "agreements")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)  // User tablosundaki user_id ile ilişki kurar
    private User user;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)  // Book tablosundaki book_id ile ilişki kurar
    private Book book;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    @Column(name = "status", nullable = false)
    private String status;



}