package com.camunda.project.delegate;

import com.camunda.project.service.BookService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckBookDelegate implements JavaDelegate {
    @Autowired
    private BookService bookService;
    @Override
    public void execute(DelegateExecution execution) {

    }
}
