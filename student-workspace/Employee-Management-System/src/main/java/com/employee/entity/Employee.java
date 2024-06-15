package com.employee.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="EMPLOYEE_NAME")
	private String employeeName;
	
	@Column(name="EMPLOYEE_DEPT")
	private String employeeDept;
	
	@Column(name="EMPLOYEE_LOC")
	private String employeeLoc;
	
	@Column(name="EMPLOYEE_PHONE_NUM")
	private String employeePhoneNo;
	
	@Column(name="ACTIVE")
	private Boolean active;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, String employeeName, String employeeDept, String employeeLoc, String employeePhoneNo,
			Boolean active) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeDept = employeeDept;
		this.employeeLoc = employeeLoc;
		this.employeePhoneNo = employeePhoneNo;
		this.active = active;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", employeeDept=" + employeeDept
				+ ", employeeLoc=" + employeeLoc + ", employeePhoneNo=" + employeePhoneNo + ", active=" + active + "]";
	}

	
	
	
	

}
