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
    public boolean checkBook(BookRequest bookRequest){ //public boolean
        try {
            final String uri = "http://localhost:8080/api/management/book/check"; //url for book check in management

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<BookRequest> requestEntity = new HttpEntity<>(bookRequest);

            ResponseEntity<List<Book>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Book>>() {
                    }
            );

            List<Book> result = response.getBody();
            //return result.get(0).getAvailable; book için available attribute eklenmeli
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
