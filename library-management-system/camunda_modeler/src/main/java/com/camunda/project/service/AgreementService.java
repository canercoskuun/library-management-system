package com.camunda.project.service;

import com.camunda.project.dto.AgreementRequest;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AgreementService {
    private final RuntimeService runtimeService;


    @Autowired
    public AgreementService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public long startModel(AgreementRequest agreementRequest) {
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("book_id", agreementRequest.getBook_id());
            variables.put("user_id", agreementRequest.getUser_id());
            variables.put("borrow_date", agreementRequest.getBorrowDate());
            variables.put("return_date", agreementRequest.getReturnDate());

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                    "StajProjectModeler-process", variables
            );
            long responseVariable = (long) runtimeService.getVariable(processInstance.getId(), "agreement_id");
            return responseVariable;



        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("e");
        }
    }
}
