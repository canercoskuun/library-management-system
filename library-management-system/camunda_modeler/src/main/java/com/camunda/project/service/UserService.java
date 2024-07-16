package com.camunda.project.service;

import com.camunda.project.dto.BookRequest;
import com.camunda.project.dto.UserRequest;
import com.camunda.project.model.Book;
import com.camunda.project.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    public boolean checkUser(UserRequest userRequest){
        try {
            final String uri = "http://localhost:8082/api/management/user/check"; //url for user check in management(Management sends request to UserRepo)

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<UserRequest> requestEntity = new HttpEntity<>(userRequest);

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
