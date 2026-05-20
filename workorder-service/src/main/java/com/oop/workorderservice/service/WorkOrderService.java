package com.oop.workorderservice.service;

import com.oop.workorderservice.dto.CreateWorkOrderRequest;
import com.oop.workorderservice.dto.UpdateWorkOrderRequest;
import com.oop.workorderservice.dto.UpdateWorkOrderStatusRequest;
import com.oop.workorderservice.dto.WorkOrderResponse;
import com.oop.workorderservice.entity.WorkOrder;
import com.oop.workorderservice.enums.WorkOrderPriority;
import com.oop.workorderservice.enums.WorkOrderStatus;
import com.oop.workorderservice.exception.DuplicateResourceException;
import com.oop.workorderservice.exception.ResourceNotFoundException;
import com.oop.workorderservice.repository.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;

    public WorkOrderService(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    public WorkOrderResponse createWorkOrder(CreateWorkOrderRequest request) {
        if (workOrderRepository.existsByWorkOrderCode(request.getWorkOrderCode())) {
            throw new DuplicateResourceException("Work order code already exists: " + request.getWorkOrderCode());
        }

        WorkOrder workOrder = new WorkOrder(
                request.getCustomerId(),
                request.getWorkOrderCode(),
                request.getVehiclePlate(),
                request.getVehicleModel(),
                request.getInspectionType(),
                request.getPriority() == null ? WorkOrderPriority.MEDIUM : request.getPriority(),
                request.getDescription(),
                request.getAssignedTo()
        );

        WorkOrder savedWorkOrder = workOrderRepository.save(workOrder);
        return mapToResponse(savedWorkOrder);
    }

    public List<WorkOrderResponse> getAllWorkOrders() {
        return workOrderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public WorkOrderResponse getWorkOrderById(Long id) {
        WorkOrder workOrder = findWorkOrderById(id);
        return mapToResponse(workOrder);
    }

    public WorkOrderResponse getWorkOrderByCode(String workOrderCode) {
        WorkOrder workOrder = workOrderRepository.findByWorkOrderCode(workOrderCode)
                .orElseThrow(() -> new ResourceNotFoundException("Work order not found with code: " + workOrderCode));

        return mapToResponse(workOrder);
    }

    public List<WorkOrderResponse> getWorkOrdersByCustomerId(Long customerId) {
        return workOrderRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<WorkOrderResponse> getWorkOrdersByStatus(WorkOrderStatus status) {
        return workOrderRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public WorkOrderResponse updateWorkOrder(Long id, UpdateWorkOrderRequest request) {
        WorkOrder workOrder = findWorkOrderById(id);

        if (request.getVehiclePlate() != null) {
            workOrder.setVehiclePlate(request.getVehiclePlate());
        }

        if (request.getVehicleModel() != null) {
            workOrder.setVehicleModel(request.getVehicleModel());
        }

        if (request.getInspectionType() != null) {
            workOrder.setInspectionType(request.getInspectionType());
        }

        if (request.getPriority() != null) {
            workOrder.setPriority(request.getPriority());
        }

        if (request.getDescription() != null) {
            workOrder.setDescription(request.getDescription());
        }

        if (request.getAssignedTo() != null) {
            workOrder.setAssignedTo(request.getAssignedTo());
        }

        WorkOrder updatedWorkOrder = workOrderRepository.save(workOrder);
        return mapToResponse(updatedWorkOrder);
    }

    public WorkOrderResponse updateStatus(Long id, UpdateWorkOrderStatusRequest request) {
        WorkOrder workOrder = findWorkOrderById(id);
        workOrder.setStatus(request.getStatus());

        WorkOrder updatedWorkOrder = workOrderRepository.save(workOrder);
        return mapToResponse(updatedWorkOrder);
    }

    public void deleteWorkOrder(Long id) {
        WorkOrder workOrder = findWorkOrderById(id);
        workOrderRepository.delete(workOrder);
    }

    private WorkOrder findWorkOrderById(Long id) {
        return workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work order not found with id: " + id));
    }

    private WorkOrderResponse mapToResponse(WorkOrder workOrder) {
        return new WorkOrderResponse(
                workOrder.getId(),
                workOrder.getCustomerId(),
                workOrder.getWorkOrderCode(),
                workOrder.getVehiclePlate(),
                workOrder.getVehicleModel(),
                workOrder.getInspectionType(),
                workOrder.getPriority(),
                workOrder.getStatus(),
                workOrder.getDescription(),
                workOrder.getAssignedTo(),
                workOrder.getCreatedAt(),
                workOrder.getUpdatedAt(),
                workOrder.getCompletedAt()
        );
    }
}