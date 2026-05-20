package com.oop.workorderservice.dto;

import com.oop.workorderservice.enums.WorkOrderPriority;

public class UpdateWorkOrderRequest {

    private String vehiclePlate;

    private String vehicleModel;

    private String inspectionType;

    private WorkOrderPriority priority;

    private String description;

    private String assignedTo;

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