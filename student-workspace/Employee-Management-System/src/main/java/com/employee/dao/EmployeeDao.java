package com.employee.dao;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeDao {

	public Employee addEmployee(Employee employee);

	public Employee updateEmployee(Employee employee);

	public Employee getEmployeeById(long id);

	public Boolean deleteEmployee(long id);

	public Page<Employee> getAllEmployees(Integer offset, Integer limit, String Sortby);

}
