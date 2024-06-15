package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.domain.EmployeeDomain;
import com.employee.entity.User;


@Service
public interface EmployeeService {
	
	public User addUser(User userInfo);

	public EmployeeDomain addEmployee(EmployeeDomain employeeDomain);
	
	public EmployeeDomain updateEmployee(EmployeeDomain employeeDomain);
	
	public EmployeeDomain getEmployeeById(Long id);
	
	public Boolean deleteEmployee(long id);
	
	public List<EmployeeDomain> getAllEmployees(Integer offset, Integer limit, String Sortby);

}