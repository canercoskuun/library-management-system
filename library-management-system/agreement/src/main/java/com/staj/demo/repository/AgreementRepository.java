package com.staj.demo.repository;

import com.staj.demo.enums.StatusType;
import com.staj.demo.model.Agreement;
import com.staj.demo.model.Book;
import com.staj.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    List<Agreement> findByUserId(Long userId);
    Agreement findByUserAndBook(User user, Book book);
}
