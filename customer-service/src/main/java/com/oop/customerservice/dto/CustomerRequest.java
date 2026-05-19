package com.oop.customerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Sector is required")
    private String sector;

    private String contactPerson;

    @Email(message = "Email format is invalid")
    private String contactEmail;

    private String phoneNumber;

    private String address;

    public CustomerRequest() {
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}