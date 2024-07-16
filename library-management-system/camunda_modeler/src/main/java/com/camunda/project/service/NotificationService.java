package com.camunda.project.service;

import com.camunda.project.dto.BookRequest;
import com.camunda.project.dto.NotificationRequest;
import com.camunda.project.model.Book;
import com.camunda.project.model.Notification;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    public List<Notification> sendNotification(NotificationRequest notificationRequest) { //model.Notification and dto.NotificationRequest should be written, they're currently empty
        try {
            final String uri = "http://localhost:8082/api/management/notification/send"; //url for sending notifications

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<NotificationRequest> requestEntity = new HttpEntity<>(notificationRequest);

            ResponseEntity<List<Notification>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<Notification>>() {
                    }
            );
            List<Notification> result = response.getBody();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
