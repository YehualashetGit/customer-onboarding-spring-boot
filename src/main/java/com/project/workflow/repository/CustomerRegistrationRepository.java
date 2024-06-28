package com.project.workflow.repository;

import com.project.workflow.entitiy.CustomerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration, Long> {
}