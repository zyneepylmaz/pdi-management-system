package com.oop.pdiservice.dto;

import com.oop.pdiservice.enums.ChecklistStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PdiChecklistResponse {

    private Long id;
    private Long customerId;
    private String vehiclePlate;
    private String vehicleModel;
    private String inspectionType;
    private ChecklistStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private List<PdiChecklistItemResponse> items;

    public PdiChecklistResponse(Long id, Long customerId, String vehiclePlate, String vehicleModel,
                                String inspectionType, ChecklistStatus status, LocalDateTime createdAt,
                                LocalDateTime completedAt, List<PdiChecklistItemResponse> items) {
        this.id = id;
        this.customerId = customerId;
        this.vehiclePlate = vehiclePlate;
        this.vehicleModel = vehicleModel;
        this.inspectionType = inspectionType;
        this.status = status;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public ChecklistStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public List<PdiChecklistItemResponse> getItems() {
        return items;
    }
}