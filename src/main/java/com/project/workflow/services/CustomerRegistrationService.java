package com.project.workflow.services;

import com.project.workflow.entitiy.CustomerRegistration;
import com.project.workflow.repository.CustomerRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRegistrationService {

    @Autowired
    private CustomerRegistrationRepository repository;

    public CustomerRegistration save(CustomerRegistration registration) {
        return repository.save(registration);
    }

    public List<CustomerRegistration> getAllRegistrations() {
        return repository.findAll();
    }

    public CustomerRegistration getRegistrationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteRegistration(Long id) {
        repository.deleteById(id);
    }
}
