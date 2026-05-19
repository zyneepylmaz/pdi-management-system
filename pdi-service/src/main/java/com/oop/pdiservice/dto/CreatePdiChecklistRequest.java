package com.oop.pdiservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreatePdiChecklistRequest {

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotBlank(message = "Vehicle plate is required")
    private String vehiclePlate;

    private String vehicleModel;

    @NotBlank(message = "Inspection type is required")
    private String inspectionType;

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

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}