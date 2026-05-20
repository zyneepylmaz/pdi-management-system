package com.oop.workorderservice.dto;

import com.oop.workorderservice.enums.WorkOrderPriority;
import com.oop.workorderservice.enums.WorkOrderStatus;

import java.time.LocalDateTime;

public class WorkOrderResponse {

    private Long id;
    private Long customerId;
    private String workOrderCode;
    private String vehiclePlate;
    private String vehicleModel;
    private String inspectionType;
    private WorkOrderPriority priority;
    private WorkOrderStatus status;
    private String description;
    private String assignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;

    public WorkOrderResponse(Long id, Long customerId, String workOrderCode, String vehiclePlate,
                             String vehicleModel, String inspectionType, WorkOrderPriority priority,
                             WorkOrderStatus status, String description, String assignedTo,
                             LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime completedAt) {
        this.id = id;
        this.customerId = customerId;
        this.workOrderCode = workOrderCode;
        this.vehiclePlate = vehiclePlate;
        this.vehicleModel = vehicleModel;
        this.inspectionType = inspectionType;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.assignedTo = assignedTo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
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

    public WorkOrderPriority getPriority() {
        return priority;
    }

    public WorkOrderStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}