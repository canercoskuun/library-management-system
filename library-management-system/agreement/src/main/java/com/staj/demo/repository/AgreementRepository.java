package com.staj.demo.repository;

import com.staj.demo.model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    List<Agreement> findByUserId(Long userId);
}
