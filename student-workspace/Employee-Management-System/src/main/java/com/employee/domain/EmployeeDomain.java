package com.employee.domain;

import java.io.Serializable;

public class EmployeeDomain implements Serializable{

  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String employeeName;
	
	private String employeeDept;
	
	private String employeeLoc;
	
	private String employeePhoneNo;
	
	private Boolean active;
	
	private long totalElements;

	private long totalRecords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDept() {
		return employeeDept;
	}

	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}

	public String getEmployeeLoc() {
		return employeeLoc;
	}

	public void setEmployeeLoc(String employeeLoc) {
		this.employeeLoc = employeeLoc;
	}

	public String getEmployeePhoneNo() {
		return employeePhoneNo;
	}

	public void setEmployeePhoneNo(String employeePhoneNo) {
		this.employeePhoneNo = employeePhoneNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	

}
