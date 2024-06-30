package com.project.workflow.entity;

import com.project.workflow.utils.Status;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customer_registrations")
public class CustomerRegistration implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(nullable = false)
    private Status status = Status.PENDING;

    // we need to store camunda process id in the database
    @Column(nullable = false)
    private String processInstanceId;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mainPurpose;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private String businessActivity;

    @Column
    private String licenseRequirements;

    @Column(nullable = false)
    private String countryOfIncorporation;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private Date dateOfIncorporation;

    @ElementCollection
    private List<String> directors;

    @ElementCollection
    private List<String> passportNumbers;

    @Column(nullable = false)
    private String designatedApplicant;

    @Column(nullable = false, unique = true)
    private String email;

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainPurpose() {
        return mainPurpose;
    }

    public void setMainPurpose(String mainPurpose) {
        this.mainPurpose = mainPurpose;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getBusinessActivity() {
        return businessActivity;
    }

    public void setBusinessActivity(String businessActivity) {
        this.businessActivity = businessActivity;
    }

    public String getLicenseRequirements() {
        return licenseRequirements;
    }

    public void setLicenseRequirements(String licenseRequirements) {
        this.licenseRequirements = licenseRequirements;
    }

    public String getCountryOfIncorporation() {
        return countryOfIncorporation;
    }

    public void setCountryOfIncorporation(String countryOfIncorporation) {
        this.countryOfIncorporation = countryOfIncorporation;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(Date dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getPassportNumbers() {
        return passportNumbers;
    }

    public void setPassportNumbers(List<String> passportNumbers) {
        this.passportNumbers = passportNumbers;
    }

    public String getDesignatedApplicant() {
        return designatedApplicant;
    }

    public void setDesignatedApplicant(String designatedApplicant) {
        this.designatedApplicant = designatedApplicant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}