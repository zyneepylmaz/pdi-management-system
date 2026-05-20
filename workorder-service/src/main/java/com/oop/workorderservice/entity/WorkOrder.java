package com.oop.workorderservice.entity;

import com.oop.workorderservice.enums.WorkOrderPriority;
import com.oop.workorderservice.enums.WorkOrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "work_orders")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Column(unique = true, nullable = false)
    private String workOrderCode;

    private String vehiclePlate;

    private String vehicleModel;

    private String inspectionType;

    @Enumerated(EnumType.STRING)
    private WorkOrderPriority priority;

    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;

    @Column(length = 1500)
    private String description;

    private String assignedTo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;

    public WorkOrder() {
    }

    public WorkOrder(Long customerId, String workOrderCode, String vehiclePlate, String vehicleModel,
                     String inspectionType, WorkOrderPriority priority, String description, String assignedTo) {
        this.customerId = customerId;
        this.workOrderCode = workOrderCode;
        this.vehiclePlate = vehiclePlate;
        this.vehicleModel = vehicleModel;
        this.inspectionType = inspectionType;
        this.priority = priority;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = WorkOrderStatus.CREATED;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = WorkOrderStatus.CREATED;
        }

        if (this.priority == null) {
            this.priority = WorkOrderPriority.MEDIUM;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();

        if (this.status == WorkOrderStatus.COMPLETED && this.completedAt == null) {
            this.completedAt = LocalDateTime.now();
        }
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

    public void setId(Long id) {
        this.id = id;
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

    public void setStatus(WorkOrderStatus status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}