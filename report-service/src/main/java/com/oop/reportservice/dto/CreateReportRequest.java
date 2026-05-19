package com.oop.reportservice.dto;

import com.oop.reportservice.enums.ReportStatus;
import jakarta.validation.constraints.NotNull;

public class CreateReportRequest {

    @NotNull(message = "PDI checklist id is required")
    private Long pdiChecklistId;

    @NotNull(message = "Customer id is required")
    private Long customerId;

    private String vehiclePlate;

    private ReportStatus status;

    private String summary;

    private String recommendation;

    private String generatedBy;

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
}