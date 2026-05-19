package com.oop.reportservice.service;

import com.oop.reportservice.dto.CreateReportRequest;
import com.oop.reportservice.dto.ReportResponse;
import com.oop.reportservice.dto.UpdateReportRequest;
import com.oop.reportservice.entity.Report;
import com.oop.reportservice.enums.ReportStatus;
import com.oop.reportservice.exception.ResourceNotFoundException;
import com.oop.reportservice.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ReportResponse createReport(CreateReportRequest request) {
        Report report = new Report(
                request.getPdiChecklistId(),
                request.getCustomerId(),
                request.getVehiclePlate(),
                request.getStatus() == null ? ReportStatus.PENDING_REVIEW : request.getStatus(),
                request.getSummary(),
                request.getRecommendation(),
                request.getGeneratedBy()
        );

        Report savedReport = reportRepository.save(report);
        return mapToResponse(savedReport);
    }

    public List<ReportResponse> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ReportResponse getReportById(Long id) {
        Report report = findReportById(id);
        return mapToResponse(report);
    }

    public ReportResponse getReportByPdiChecklistId(Long pdiChecklistId) {
        Report report = reportRepository.findByPdiChecklistId(pdiChecklistId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Report not found for PDI checklist id: " + pdiChecklistId
                ));

        return mapToResponse(report);
    }

    public List<ReportResponse> getReportsByCustomerId(Long customerId) {
        return reportRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ReportResponse updateReport(Long id, UpdateReportRequest request) {
        Report report = findReportById(id);

        if (request.getStatus() != null) {
            report.setStatus(request.getStatus());
        }

        if (request.getSummary() != null) {
            report.setSummary(request.getSummary());
        }

        if (request.getRecommendation() != null) {
            report.setRecommendation(request.getRecommendation());
        }

        Report updatedReport = reportRepository.save(report);
        return mapToResponse(updatedReport);
    }

    public void deleteReport(Long id) {
        Report report = findReportById(id);
        reportRepository.delete(report);
    }

    private Report findReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
    }

    private ReportResponse mapToResponse(Report report) {
        return new ReportResponse(
                report.getId(),
                report.getPdiChecklistId(),
                report.getCustomerId(),
                report.getVehiclePlate(),
                report.getStatus(),
                report.getSummary(),
                report.getRecommendation(),
                report.getGeneratedBy(),
                report.getGeneratedAt(),
                report.getUpdatedAt()
        );
    }
}