package com.camunda.project.service;

import com.camunda.project.dto.AgreementRequest;
import com.camunda.project.dto.Response;
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

    public Response startModel(AgreementRequest agreementRequest) {
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("book_id", agreementRequest.getBook_id());
            variables.put("user_id", agreementRequest.getUser_id());
            variables.put("borrow_date", agreementRequest.getBorrowDate());
            variables.put("return_date", agreementRequest.getReturnDate());

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                    "StajProjectModeler-process", variables
            );
            return new Response(200,"Model worked succesfully.", "Process instance id: " + processInstance.getId());
        } catch (Exception e) {
            e.printStackTrace();
           return new Response(500, "An error occured.", null);
        }
    }
}
