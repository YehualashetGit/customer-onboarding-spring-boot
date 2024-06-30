package com.project.workflow.services;

import com.project.workflow.entity.CustomerRegistration;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
public class GenerateApplicationID implements JavaDelegate {

    @Autowired
    private CustomerRegistrationService service;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long registrationId = (Long) execution.getVariable("registrationId");
        CustomerRegistration registration = service.getRegistrationById(registrationId);

        if (registration == null) {
            throw new Exception("Generate Application ID failed: Registration not found.");
        }

        String applicationID = UUID.randomUUID().toString();
        registration.setProcessInstanceId(applicationID);
        service.save(registration);

        execution.setVariable("applicationID", applicationID);
    }
}