package com.report.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.report.entity.IpcReport;
import com.report.entity.ReportRequest;
import com.report.service.IpcReportService;

@RestController
@RequestMapping("/api/ipcReports")
public class IpcReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpcReportController.class);

    @Autowired
    private IpcReportService ipcReportService;

    @PostMapping(value="/addReport")
    public IpcReport generateReport(@RequestBody ReportRequest request) {
        try {
            return ipcReportService.generateReport(request.getReportName(), request.getFormat(), request.getReportData());
        } catch (Exception e) {
            LOGGER.error("Error generating report", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating report", e);
        }
    }

    @GetMapping("/{id}")
    public IpcReport findById(@PathVariable Long id) {
        IpcReport ipcReport = ipcReportService.findById(id);
        if (ipcReport == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
        return ipcReport;
    }
}
