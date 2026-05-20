package com.oop.workorderservice.controller;

import com.oop.workorderservice.dto.CreateWorkOrderRequest;
import com.oop.workorderservice.dto.UpdateWorkOrderRequest;
import com.oop.workorderservice.dto.UpdateWorkOrderStatusRequest;
import com.oop.workorderservice.dto.WorkOrderResponse;
import com.oop.workorderservice.enums.WorkOrderStatus;
import com.oop.workorderservice.service.WorkOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workorders")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @PostMapping
    public ResponseEntity<WorkOrderResponse> createWorkOrder(
            @Valid @RequestBody CreateWorkOrderRequest request
    ) {
        WorkOrderResponse response = workOrderService.createWorkOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkOrderResponse>> getAllWorkOrders() {
        return ResponseEntity.ok(workOrderService.getAllWorkOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkOrderResponse> getWorkOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(workOrderService.getWorkOrderById(id));
    }

    @GetMapping("/code/{workOrderCode}")
    public ResponseEntity<WorkOrderResponse> getWorkOrderByCode(@PathVariable String workOrderCode) {
        return ResponseEntity.ok(workOrderService.getWorkOrderByCode(workOrderCode));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<WorkOrderResponse>> getWorkOrdersByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(workOrderService.getWorkOrdersByCustomerId(customerId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<WorkOrderResponse>> getWorkOrdersByStatus(@PathVariable WorkOrderStatus status) {
        return ResponseEntity.ok(workOrderService.getWorkOrdersByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkOrderResponse> updateWorkOrder(
            @PathVariable Long id,
            @RequestBody UpdateWorkOrderRequest request
    ) {
        return ResponseEntity.ok(workOrderService.updateWorkOrder(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<WorkOrderResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateWorkOrderStatusRequest request
    ) {
        return ResponseEntity.ok(workOrderService.updateStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable Long id) {
        workOrderService.deleteWorkOrder(id);
        return ResponseEntity.noContent().build();
    }
}