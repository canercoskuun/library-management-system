package com.camunda.project.service;

import com.camunda.project.dto.AgreementRequest;
import com.camunda.project.dto.BookRequest;
import com.camunda.project.dto.UserRequest;
import com.camunda.project.model.Agreement;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgreementService {
    private final BookService bookService;
    private final UserService userService;
    private final RuntimeService runtimeService;

    @Autowired
    public AgreementService(BookService bookService, UserService userService, RuntimeService runtimeService){
        this.bookService = bookService;
        this.userService = userService;
        this.runtimeService = runtimeService;
    }

    public List<Agreement> createAgreement(AgreementRequest agreementRequest) {
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("book_id",agreementRequest.getBook_id());
            variables.put("user_id", agreementRequest.getUser_id());
            variables.put("borrow_date", agreementRequest.getBorrowDate());
            variables.put("return_date", agreementRequest.getReturnDate());
            BookRequest book_request = new BookRequest(agreementRequest.getBook_id());
            variables.put("book_available", bookService.checkBook(book_request));
            UserRequest user_request = new UserRequest(agreementRequest.getUser_id());
            variables.put("user_trustable", userService.checkUser(user_request));

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                    "process", variables
            );

            final String uri = "http://localhost:8080/api/management/agreement/create"; //url for agreement creation process in management

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
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
