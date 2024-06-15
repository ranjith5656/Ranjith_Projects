package com.report.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ReportRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String format;
	
	private Long id;

	private String product;

	private Double price;

	private Integer quantity;

	private LocalDate date;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
