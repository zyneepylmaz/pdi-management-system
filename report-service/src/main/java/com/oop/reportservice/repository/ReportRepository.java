package com.oop.reportservice.repository;

import com.oop.reportservice.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByPdiChecklistId(Long pdiChecklistId);

    List<Report> findByCustomerId(Long customerId);
}