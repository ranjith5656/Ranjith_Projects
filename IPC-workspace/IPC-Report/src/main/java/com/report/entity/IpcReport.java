package com.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.report.format.ReportFormat;

@Entity
@Table(name = "IPC_REPORT")
public class IpcReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REPORT_NAME")
    private String reportName;

    @Column(name = "REPORT_DATA")
    private byte[] reportData;

    @Column(name = "FORMAT")
    @Enumerated(EnumType.STRING)
    private ReportFormat format;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public byte[] getReportData() {
		return reportData;
	}

	public void setReportData(byte[] reportData) {
		this.reportData = reportData;
	}

	public ReportFormat getFormat() {
		return format;
	}

	public void setFormat(ReportFormat format) {
		this.format = format;
	}

	public IpcReport(Long id, String reportName, byte[] reportData, ReportFormat format) {
		super();
		this.id = id;
		this.reportName = reportName;
		this.reportData = reportData;
		this.format = format;
	}

	public IpcReport() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
