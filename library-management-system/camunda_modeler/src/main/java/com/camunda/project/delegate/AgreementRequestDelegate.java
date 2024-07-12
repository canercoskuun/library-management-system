package com.camunda.project.delegate;

import com.camunda.project.dto.AgreementRequest;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AgreementRequestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        long book_id = (Long) execution.getVariable("book_id");
        long user_id = (Long) execution.getVariable("user_id");
        Date borrow_date = (Date) execution.getVariable("borrow_date");
        Date return_date = (Date) execution.getVariable("return_date");


        AgreementRequest agreementRequest = new AgreementRequest();
        agreementRequest.setBook_id(book_id);
        agreementRequest.setUser_id(user_id);
        agreementRequest.setBorrowDate(borrow_date);
        agreementRequest.setReturnDate(return_date);

        try {
            System.out.println("Agreement request created successfully.");
        } catch (Exception e) {
            System.err.println("Error while creating agreement request: " + e.getMessage());
        }
    }
}
