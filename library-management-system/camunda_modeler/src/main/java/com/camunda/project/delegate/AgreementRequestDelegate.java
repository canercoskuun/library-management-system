package com.camunda.project.delegate;

import com.camunda.project.dto.AgreementRequest;
import com.camunda.project.model.Agreement;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Component
public class AgreementRequestDelegate implements JavaDelegate {
    Expression serviceUrl;

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

        final String uri = "http://localhost:8082/api/management/" + serviceUrl.getExpressionText();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AgreementRequest> requestEntity = new HttpEntity<>(agreementRequest);

        ResponseEntity<List<Agreement>> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Agreement>>() {
                }
        );

        List<Agreement> result = response.getBody();
        throw new RuntimeException("e");
    }
}
