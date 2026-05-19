package com.oop.reportservice.controller;

import com.oop.reportservice.dto.CreateReportRequest;
import com.oop.reportservice.dto.ReportResponse;
import com.oop.reportservice.dto.UpdateReportRequest;
import com.oop.reportservice.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@Valid @RequestBody CreateReportRequest request) {
        ReportResponse response = reportService.createReport(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    @GetMapping("/checklist/{pdiChecklistId}")
    public ResponseEntity<ReportResponse> getReportByPdiChecklistId(@PathVariable Long pdiChecklistId) {
        return ResponseEntity.ok(reportService.getReportByPdiChecklistId(pdiChecklistId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReportResponse>> getReportsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(reportService.getReportsByCustomerId(customerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportResponse> updateReport(
            @PathVariable Long id,
            @RequestBody UpdateReportRequest request
    ) {
        return ResponseEntity.ok(reportService.updateReport(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}