package com.project.workflow.services;

import com.project.workflow.entity.CustomerRegistration;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ValidateFormFields implements JavaDelegate {

    @Autowired
    private CustomerRegistrationService service;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long registrationId = (Long) execution.getVariable("registrationId");
        CustomerRegistration registration = service.getRegistrationById(registrationId);

        if (registration == null) {
            throw new Exception("Validation failed: Registration not found.");
        }

        // Perform validation logic here
        if (registration.getCompanyName() == null || registration.getCompanyName().isEmpty() ||
                registration.getEntityType() == null || registration.getEntityType().isEmpty() ||
                registration.getBusinessActivity() == null || registration.getBusinessActivity().isEmpty() ||
                registration.getCountryOfIncorporation() == null || registration.getCountryOfIncorporation().isEmpty() ||
                registration.getRegistrationNumber() == null || registration.getRegistrationNumber().isEmpty() ||
                registration.getDateOfIncorporation() == null ||
                registration.getDirectors() == null || registration.getDirectors().isEmpty() ||
                registration.getPassportNumbers() == null || registration.getPassportNumbers().isEmpty() ||
                registration.getDesignatedApplicant() == null || registration.getDesignatedApplicant().isEmpty() ||
                registration.getEmail() == null || registration.getEmail().isEmpty()) {

            throw new Exception("Validation failed: One or more required fields are missing or invalid.");
        }
    }
}