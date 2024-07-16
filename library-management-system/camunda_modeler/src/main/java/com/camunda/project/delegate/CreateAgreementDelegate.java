package com.camunda.project.delegate;

import com.camunda.project.service.AgreementService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAgreementDelegate implements JavaDelegate {
    @Autowired
    private AgreementService agreementService;
    @Override
    public void execute(DelegateExecution execution) {
        try {
            System.out.println("Agreement created successfully.");
        } catch (Exception e) {
            System.err.println("Error while creating borrow: " + e.getMessage());
        }
    }

}
