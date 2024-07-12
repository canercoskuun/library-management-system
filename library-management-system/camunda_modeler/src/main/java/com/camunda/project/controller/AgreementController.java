package com.camunda.project.controller;

import com.camunda.project.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/model/agreement")
public class AgreementController {
    private final AgreementService agreementService;
    @Autowired
    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }




}
