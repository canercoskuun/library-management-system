package com.camunda.project.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CheckBookDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        boolean book_av = (Boolean) execution.getVariable("book_available");

        if(!book_av){
            throw new RuntimeException("Book is not available!");
        }
        execution.setVariable("book_available", book_av);

    }
}
