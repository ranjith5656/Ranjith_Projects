package com.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.report.repository.SaleRepository;
import com.report.service.ReportService;

@Configuration
public class ReportConfig {

  @Bean
  public ReportService reportService(SaleRepository saleRepository) {
    return new ReportService(saleRepository);
  }
}

