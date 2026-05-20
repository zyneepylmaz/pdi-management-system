package com.oop.workorderservice.repository;

import com.oop.workorderservice.entity.WorkOrder;
import com.oop.workorderservice.enums.WorkOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    List<WorkOrder> findByCustomerId(Long customerId);

    List<WorkOrder> findByStatus(WorkOrderStatus status);

    Optional<WorkOrder> findByWorkOrderCode(String workOrderCode);

    boolean existsByWorkOrderCode(String workOrderCode);
}