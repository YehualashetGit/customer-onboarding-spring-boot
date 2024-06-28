package com.project.workflow.controllers;

import com.project.workflow.entitiy.CustomerRegistration;
import com.project.workflow.services.CustomerRegistrationService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registrations")
public class CustomerRegistrationController {

    @Autowired
    private RuntimeService runtimeService;


    @Autowired
    private CustomerRegistrationService service;

    @PostMapping("/submit")
    public String submitRegistration(@RequestBody CustomerRegistration registration) {
         service.save(registration);
        Map<String, Object> variables = new HashMap<>();
        variables.put("purpose", registration.getMainPurpose());
        variables.put("companyName", registration.getCompanyName());
        variables.put("entityType", registration.getEntityType());
        variables.put("businessActivity", registration.getBusinessActivity());
        variables.put("licenseRequirements", registration.getLicenseRequirements());
        variables.put("countryOfIncorporation", registration.getCountryOfIncorporation());
        variables.put("registrationNumber", registration.getRegistrationNumber());
        variables.put("dateOfIncorporation", registration.getDateOfIncorporation());
        variables.put("directorNames", registration.getDirectors());
        variables.put("directorPassportNumbers", registration.getPassportNumbers());
        variables.put("designatedApplicant", registration.getDesignatedApplicant());
        variables.put("email", registration.getEmail());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("customerOnboardingProcess", variables);
        return "Registration submitted. Process Instance ID: " + processInstance.getId();

    }

    @GetMapping
    public List<CustomerRegistration> getAllRegistrations() {
        return service.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public CustomerRegistration getRegistrationById(@PathVariable Long id) {
        return service.getRegistrationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRegistration(@PathVariable Long id) {
        service.deleteRegistration(id);
    }
}