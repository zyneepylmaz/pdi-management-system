package com.oop.reportservice.entity;

import com.oop.reportservice.enums.ReportStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pdiChecklistId;

    private Long customerId;

    private String vehiclePlate;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(length = 2000)
    private String summary;

    @Column(length = 2000)
    private String recommendation;

    private String generatedBy;

    private LocalDateTime generatedAt;

    private LocalDateTime updatedAt;

    public Report() {
    }

    public Report(Long pdiChecklistId, Long customerId, String vehiclePlate,
                  ReportStatus status, String summary, String recommendation, String generatedBy) {
        this.pdiChecklistId = pdiChecklistId;
        this.customerId = customerId;
        this.vehiclePlate = vehiclePlate;
        this.status = status;
        this.summary = summary;
        this.recommendation = recommendation;
        this.generatedBy = generatedBy;
    }

    @PrePersist
    public void onCreate() {
        this.generatedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = ReportStatus.PENDING_REVIEW;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getPdiChecklistId() {
        return pdiChecklistId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPdiChecklistId(Long pdiChecklistId) {
        this.pdiChecklistId = pdiChecklistId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}