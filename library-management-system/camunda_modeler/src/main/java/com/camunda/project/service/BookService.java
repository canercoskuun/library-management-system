package com.camunda.project.service;

import com.camunda.project.dto.AgreementRequest;
import com.camunda.project.dto.BookRequest;
import com.camunda.project.model.Agreement;
import com.camunda.project.model.Book;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {
    public boolean checkBook(BookRequest bookRequest){
        try {
            final String uri = "http://localhost:8082/api/management/book/check"; //url for book check in management (Management sends requests to BookRepo)

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BookRequest> requestEntity = new HttpEntity<>(bookRequest);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Boolean>() {
                    }
            );
            Boolean result = response.getBody();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
