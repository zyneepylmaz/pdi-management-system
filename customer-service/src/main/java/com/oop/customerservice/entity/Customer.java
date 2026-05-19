package com.oop.customerservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String sector;

    private String contactPerson;

    private String contactEmail;

    private String phoneNumber;

    private String address;

    private Boolean active;

    private LocalDateTime createdAt;

    public Customer() {
    }

    public Customer(String companyName, String sector, String contactPerson, String contactEmail,
                    String phoneNumber, String address, Boolean active) {
        this.companyName = companyName;
        this.sector = sector;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.active = active;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.active == null) {
            this.active = true;
        }
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

    public void setId(Long id) {
        this.id = id;
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

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}