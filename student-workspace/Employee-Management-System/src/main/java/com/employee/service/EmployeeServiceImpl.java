package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeDao;
import com.employee.domain.EmployeeDomain;
import com.employee.entity.Employee;
import com.employee.entity.User;
import com.employee.objectcopier.ObjectCopier;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public User addUser(User userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		return repository.save(userInfo);
	}

	@Transactional
	public EmployeeDomain addEmployee(EmployeeDomain employeeDomain) {
		return toDomain(employeeDao.addEmployee(toEntity(employeeDomain)));
	}

	@Transactional
	public EmployeeDomain updateEmployee(EmployeeDomain employeeDomain) {
		return toDomain(employeeDao.updateEmployee(toEntity(employeeDomain)));
	}

	@Transactional
	public EmployeeDomain getEmployeeById(Long id) {
		return toDomain(employeeDao.getEmployeeById(id));
	}

//	private static List<EmployeeDomain> list = new ArrayList<>();
//	static{
//		list.add(new EmployeeDomain(1,"Ranjith","Java","Hyderabad","9876543210"));
//		
//	}
	@Transactional
	public List<EmployeeDomain> getAllEmployees(Integer offset, Integer limit, String Sortby) {
		Page<com.employee.entity.Employee> page = employeeDao.getAllEmployees(offset, limit, Sortby);
		List<EmployeeDomain> list = new ArrayList<>();
		page.forEach(studentDetails -> {
			EmployeeDomain domain = toDomain(studentDetails);
			domain.setTotalRecords(page.getTotalElements());
			list.add(domain);
		});
		return list;
	}

	@Transactional
	public Boolean deleteEmployee(long id) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		if (employee != null) {
			employee.setActive(false);
			employee = employeeRepository.save(employee);
			return true;
		}
		return false;
	}
	
	
    // creating toDomiain method
	private EmployeeDomain toDomain(Employee employee) {
		EmployeeDomain domain = null;
		if (employee != null) {
			domain = new com.employee.domain.EmployeeDomain();
			ObjectCopier.copyObject(employee, domain);
		}
		return domain;
	}

	// creating toEntity method
	private Employee toEntity(EmployeeDomain employeeDomain) {
		Employee entity = null;
		if (employeeDomain != null) {
			entity = new com.employee.entity.Employee();
			ObjectCopier.copyObject(employeeDomain, entity);
		}

		return entity;
	}

}
