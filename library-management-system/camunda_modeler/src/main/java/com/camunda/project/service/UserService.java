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

    public boolean checkUser(UserRequest userRequest){ //public boolean
        try {
            final String uri = "http://localhost:8080/api/management/user/check"; //url for member check in management

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<UserRequest> requestEntity = new HttpEntity<>(userRequest);

            ResponseEntity<List<User>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<User>>() {
                    }
            );

            List<User> result = response.getBody();
            //return result.get(0).getTrustable; user -> trustable (?) ya da ?exists
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
