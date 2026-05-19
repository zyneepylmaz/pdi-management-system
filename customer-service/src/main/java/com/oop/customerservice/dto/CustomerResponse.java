package com.oop.customerservice.dto;

import java.time.LocalDateTime;

public class CustomerResponse {

    private Long id;
    private String companyName;
    private String sector;
    private String contactPerson;
    private String contactEmail;
    private String phoneNumber;
    private String address;
    private Boolean active;
    private LocalDateTime createdAt;

    public CustomerResponse() {
    }

    public CustomerResponse(Long id, String companyName, String sector, String contactPerson,
                            String contactEmail, String phoneNumber, String address,
                            Boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.companyName = companyName;
        this.sector = sector;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSector() {
        return sector;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}