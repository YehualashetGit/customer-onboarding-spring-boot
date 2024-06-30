package com.project.workflow.controllers;


import com.project.workflow.entity.CustomerRegistration;
import com.project.workflow.services.CustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/approver")
public class ApproverController {

    @Autowired
    private CustomerRegistrationService service;

    @GetMapping("/applications")
    public List<CustomerRegistration> getApplicationsForApproval() {
        // Logic to get only the applications that have been processed by the processing team
        return service.getProcessedRegistrations();
    }

    @PostMapping("/applications/{id}/approve")
    public void approveApplication(@PathVariable Long id) {
        // Logic to approve the application
    }

    @PostMapping("/applications/{id}/reject")
    public void rejectApplication(@PathVariable Long id) {
        // Logic to reject the application
    }
}
