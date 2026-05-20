package com.oop.workorderservice.dto;

import com.oop.workorderservice.enums.WorkOrderPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateWorkOrderRequest {

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotBlank(message = "Work order code is required")
    private String workOrderCode;

    @NotBlank(message = "Vehicle plate is required")
    private String vehiclePlate;

    private String vehicleModel;

    @NotBlank(message = "Inspection type is required")
    private String inspectionType;

    private WorkOrderPriority priority;

    private String description;

    private String assignedTo;

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

    public String getDescription() {
        return description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public void setPriority(WorkOrderPriority priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}