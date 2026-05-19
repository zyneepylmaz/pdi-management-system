package com.oop.reportservice.dto;

import com.oop.reportservice.enums.ReportStatus;

public class UpdateReportRequest {

    private ReportStatus status;

    private String summary;

    private String recommendation;

    public ReportStatus getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}