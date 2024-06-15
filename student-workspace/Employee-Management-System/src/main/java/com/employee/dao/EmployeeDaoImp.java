package com.employee.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;
import com.employee.notfoundexecption.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

import jakarta.transaction.Transactional;


@Repository
public class EmployeeDaoImp implements EmployeeDao{
	
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public Employee addEmployee(Employee employee) {

		return employeeRepository.saveAndFlush(employee);
	}

	@Transactional
	public Employee updateEmployee(Employee studentEntity) {
		Employee entity = employeeRepository.findById(studentEntity.getId()).orElse(null);
		if (entity == null) {
			throw new EmployeeNotFoundException("Employee is not Available please checkout..");
		}
		return employeeRepository.saveAndFlush(studentEntity);
	}

	public Employee getEmployeeById(long id) {

		return employeeRepository.findById(id).orElse(null);
	}

	@Transactional
	public Boolean deleteEmployee(long id) {
		Boolean b= false;
		Employee employee = getEmployeeById(id);
		if(employee==null) {
		throw new EmployeeNotFoundException("Employee is null ..");
		}
		else {
			employeeRepository.deleteById(id);
		b = true;
		}
	
		return b;
	}

	@Override
	public Page<Employee> getAllEmployees(Integer offset, Integer limit, String Sortby) {
		Pageable pageable = PageRequest.of(offset, limit, Sort.by("id").descending());
		return employeeRepository.findAll(pageable);
	}


}
