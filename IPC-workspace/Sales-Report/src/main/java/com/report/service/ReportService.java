package com.report.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.entity.Sale;
import com.report.repository.SaleRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportService {
  private final SaleRepository saleRepository;

  @Autowired
  public ReportService(SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  public byte[] generateReport(String format) throws Exception {
    List<Sale> sales = saleRepository.findAll();

    switch (format) {
      case "pdf":
        return generatePdfReport(sales);
      case "xlsx":
        return generateExcelReport(sales);
      default:
        throw new Exception("Invalid format specified: " + format);
    }
  }

  private byte[] generatePdfReport(List<Sale> sales) throws JRException {
    // Load the JasperReport template
    InputStream inputStream = getClass().getResourceAsStream("/report.jrxml");
    JasperReport report = JasperCompileManager.compileReport(inputStream);

    // Fill the report with data
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("sales", sales);
    JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

    // Export the report to PDF
    byte[] pdfReport = JasperExportManager.exportReportToPdf(print);
    return pdfReport;
  }

  private byte[] generateExcelReport(List<Sale> sales) throws IOException {
    // Create a new Excel workbook
    XSSFWorkbook workbook = new XSSFWorkbook();

    // Create a new sheet in the workbook
    XSSFSheet sheet = workbook.createSheet("Sales");

    // Create the header row
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Product");
    headerRow.createCell(1).setCellValue("Price");
    headerRow.createCell(2).setCellValue("Quantity");
    headerRow.createCell(3).setCellValue("Date");

    // Fill in the data for each sale
    int rowNum = 1;
    for (Sale sale : sales) {
      Row dataRow = sheet.createRow(rowNum++);
      dataRow.createCell(0).setCellValue(sale.getProduct());
      dataRow.createCell(1).setCellValue(sale.getPrice());
      dataRow.createCell(2).setCellValue(sale.getQuantity());
      dataRow.createCell(3).setCellValue(sale.getDate().toString());
    }

    // Write the workbook to a byte array
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    workbook.write(baos);
    workbook.close();
    return baos.toByteArray();
  }
}

