package com.camunda.project.delegate;

import com.camunda.project.service.NotificationService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationDelegate implements JavaDelegate {
    @Autowired
    private NotificationService notificationService;
    @Override
    public void execute(DelegateExecution execution) {

    }

}
