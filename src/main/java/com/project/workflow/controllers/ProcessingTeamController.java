package com.project.workflow.controllers;


import com.project.workflow.entity.CustomerRegistration;
import com.project.workflow.services.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processing-team")
public class ProcessingTeamController {

    @Autowired
    private CustomerRegistrationService service;

    @GetMapping("/applications")
    public List<CustomerRegistration> getAllApplications() {
        return service.getAllRegistrations();
    }

    @GetMapping("/applications/{id}")
    public CustomerRegistration getApplicationById(@PathVariable Long id) {
        return service.getRegistrationById(id);
    }

    @PutMapping("/applications/{id}")
    public CustomerRegistration updateApplication(@PathVariable Long id, @RequestBody CustomerRegistration registration) {
        registration.setId(id);
        return service.submitRegistration(registration);
    }

    @PostMapping("/applications/{id}/proceed")
    public void proceedApplication(@PathVariable Long id) {
        if (service.getRegistrationById(id) == null) {
            throw new RuntimeException("Application not found");
        }

        service.setProcessed(id);

        // update the process instance of camuda and move to the next task

    }

    @PostMapping("/applications/{id}/reject")
    public void rejectApplication(@PathVariable Long id) {
        // Logic to reject the application
        if (service.getRegistrationById(id) == null) {
            throw new RuntimeException("Application not found");
        }

        service.setRejected(id);

    }
}
