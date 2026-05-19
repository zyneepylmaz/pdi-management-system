package com.oop.pdiservice.entity;

import com.oop.pdiservice.enums.ChecklistStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pdi_checklists")
public class PdiChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String vehiclePlate;

    private String vehicleModel;

    private String inspectionType;

    @Enumerated(EnumType.STRING)
    private ChecklistStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PdiChecklistItem> items = new ArrayList<>();

    public PdiChecklist() {
    }

    public PdiChecklist(Long customerId, String vehiclePlate, String vehicleModel, String inspectionType) {
        this.customerId = customerId;
        this.vehiclePlate = vehiclePlate;
        this.vehicleModel = vehicleModel;
        this.inspectionType = inspectionType;
        this.status = ChecklistStatus.CREATED;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = ChecklistStatus.CREATED;
        }
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

    public List<PdiChecklistItem> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setStatus(ChecklistStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setItems(List<PdiChecklistItem> items) {
        this.items = items;
    }
}