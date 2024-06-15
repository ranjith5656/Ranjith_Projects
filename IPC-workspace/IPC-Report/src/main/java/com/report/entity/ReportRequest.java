package com.report.entity;

import java.util.Map;

import com.report.format.ReportFormat;

public class ReportRequest{
    private String reportName;
    private ReportFormat  format;
    private Map<String, Object> reportData;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    

    

	public ReportFormat getFormat() {
		return format;
	}

	public void setFormat(ReportFormat format) {
		this.format = format;
	}

	public Map<String, Object> getReportData() {
        return reportData;
    }

    public void setReportData(Map<String, Object> reportData) {
        this.reportData = reportData;
    }
}

