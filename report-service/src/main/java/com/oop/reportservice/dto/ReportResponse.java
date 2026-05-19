package com.oop.reportservice.dto;

import com.oop.reportservice.enums.ReportStatus;

import java.time.LocalDateTime;

public class ReportResponse {

    private Long id;
    private Long pdiChecklistId;
    private Long customerId;
    private String vehiclePlate;
    private ReportStatus status;
    private String summary;
    private String recommendation;
    private String generatedBy;
    private LocalDateTime generatedAt;
    private LocalDateTime updatedAt;

    public ReportResponse(Long id, Long pdiChecklistId, Long customerId, String vehiclePlate,
                          ReportStatus status, String summary, String recommendation,
                          String generatedBy, LocalDateTime generatedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.pdiChecklistId = pdiChecklistId;
        this.customerId = customerId;
        this.vehiclePlate = vehiclePlate;
        this.status = status;
        this.summary = summary;
        this.recommendation = recommendation;
        this.generatedBy = generatedBy;
        this.generatedAt = generatedAt;
        this.updatedAt = updatedAt;
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
}