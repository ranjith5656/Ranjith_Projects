package com.report.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.entity.IpcReport;
import com.report.entity.ReportRequest;
import com.report.format.ReportFormat;
import com.report.repository.IpcReportRepository;

@Service
public class IpcReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpcReportService.class);

    @Autowired
    private IpcReportRepository ipcReportRepository;

    public IpcReport generateReport(String reportName, ReportFormat format, Map<String, Object> reportData) throws Exception {
        byte[] reportDataBytes = generateReportData(format, reportData);
        IpcReport ipcReport = new IpcReport();
        ipcReport.setReportName(reportName);
        ipcReport.setReportData(reportDataBytes);
        ipcReport.setFormat(format);
        return ipcReportRepository.save(ipcReport);
    }

    private byte[] generateReportData(ReportFormat format, Map<String, Object> reportData) throws Exception {
        // logic to generate report data in the specified format using the reportData
        return new byte[0];
    }

    public IpcReport findById(Long id) {
        return ipcReportRepository.findById(id).orElse(null);
    }
}

