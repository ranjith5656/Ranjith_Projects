package com.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.report.dto.ReportRequest;
import com.report.entity.Sale;
import com.report.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

  private final ReportService reportService;

  @Autowired
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @PostMapping(value="/generateReport")
  public ResponseEntity<byte[]> generateReport(@RequestBody ReportRequest reportRequest) throws Exception {
    byte[] report = reportService.generateReport(reportRequest.getFormat());
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report." + reportRequest.getFormat() + "\"")
        .body(report);
  }

//  @GetMapping
//  public List<Sale> getSales() {
//    return reportService.getSales();
//  }
}

