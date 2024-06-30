package com.project.workflow.controllers;

import com.project.workflow.entity.CustomerRegistration;
import com.project.workflow.services.CustomerRegistrationService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class CustomerRegistrationController {

    @Autowired
    private RuntimeService runtimeService;


    @Autowired
    private CustomerRegistrationService service;

    @PostMapping("/submit")
    public String submitRegistration(@RequestBody CustomerRegistration registration) {
        try {
            CustomerRegistration savedRegistration = service.submitRegistration(registration);
            return "Registration submitted. Process Instance ID: " + savedRegistration.getProcessInstanceId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to submit the registration", e);
        }
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