package com.camunda.project.delegate;

import com.camunda.project.service.UserService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckUserDelegate implements JavaDelegate {
    @Autowired
    private UserService userService;
    @Override
    public void execute(DelegateExecution execution) {

    }
}
