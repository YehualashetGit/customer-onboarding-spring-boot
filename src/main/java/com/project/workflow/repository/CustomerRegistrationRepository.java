package com.project.workflow.repository;

import com.project.workflow.entity.CustomerRegistration;
import com.project.workflow.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration, Long> {
    List<CustomerRegistration> findByStatus(Status status);
}