package com.project.workflow;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class CamundaConfig {

    private final RepositoryService repositoryService;

    public CamundaConfig(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @PostConstruct
    public void deployProcesses() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("customer-registration.bpmn")
                .name("Customer Registration Process")
                .deploy();
    }



}