package com.oop.workorderservice.dto;

import com.oop.workorderservice.enums.WorkOrderStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateWorkOrderStatusRequest {

    @NotNull(message = "Work order status is required")
    private WorkOrderStatus status;

    public WorkOrderStatus getStatus() {
        return status;
    }

    public void setStatus(WorkOrderStatus status) {
        this.status = status;
    }
}