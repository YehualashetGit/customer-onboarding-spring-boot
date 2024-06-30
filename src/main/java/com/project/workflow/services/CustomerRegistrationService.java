package com.project.workflow.services;

import com.project.workflow.entity.CustomerRegistration;
import com.project.workflow.repository.CustomerRegistrationRepository;
import com.project.workflow.utils.Status;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerRegistrationService {

    @Autowired
    private CustomerRegistrationRepository repository;

    @Autowired
    private RuntimeService runtimeService;

    public CustomerRegistration submitRegistration(CustomerRegistration registration) {
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
        variables.put("status", Status.SUBMITTED.toString());
        try {
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("customerOnboardingProcess", variables);

            registration.setProcessInstanceId(processInstance.getId());
            registration.setStatus(Status.SUBMITTED);
            return repository.save(registration);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start the process instance in Camunda", e);
        }

    }

    public CustomerRegistration save(CustomerRegistration registration) {
        return repository.save(registration);
    }


    public List<CustomerRegistration> getAllRegistrations() {
        return repository.findAll();
    }

    public CustomerRegistration getRegistrationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void setRegistrationById(Long id, CustomerRegistration registration) {
        registration.setId(id);
        repository.save(registration);
    }

    public void deleteRegistration(Long id) {
        repository.deleteById(id);
    }

    public List<CustomerRegistration> getProcessedRegistrations() {
        // Logic to get applications that have been processed by the processing team
        return repository.findByStatus(Status.PROCESSED);
    }

    public List<CustomerRegistration> getRejectedRegistrations() {
        // Logic to get applications that have been rejected by the processing team
        return repository.findByStatus(Status.REJECTED);
    }


    public void setProcessed(Long id) {
        CustomerRegistration registration = repository.findById(id).orElse(null);
        if (registration != null) {
            registration.setStatus(Status.PROCESSED);
            repository.save(registration);
        }

    }

    public void setRejected(Long id) {
        CustomerRegistration registration = repository.findById(id).orElse(null);
        if (registration != null) {
            registration.setStatus(Status.REJECTED);
            repository.save(registration);
        }
    }
}
