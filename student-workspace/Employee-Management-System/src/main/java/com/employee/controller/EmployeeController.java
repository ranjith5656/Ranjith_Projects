package com.employee.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.domain.AuthRequest;
import com.employee.domain.EmployeeDomain;
import com.employee.entity.User;
import com.employee.response.status.Response;
import com.employee.response.status.StatusType;
import com.employee.security.config.JwtService;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/addUser")
	public ResponseEntity<User> addNewUser(@RequestBody User userInfo) {
		return new ResponseEntity<>(service.addUser(userInfo), HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/authenticate")
	public ResponseEntity<Response> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			if (authentication.isAuthenticated()) {
				String token = jwtService.generateToken(authRequest.getUsername());
				Response response = new Response();
				response.setStatusType(StatusType.SUCCESS);
				response.setCode("200");
				response.setMessage("Token generated Successfully");
				response.setData(token);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} else {
				Response response = new Response();
				response.setStatusType(StatusType.ERROR);
				response.setCode("401");
				response.setMessage("User credentials not Found");
				return new ResponseEntity<Response>(HttpStatus.UNAUTHORIZED);
			}
		} catch (AuthenticationException e) {
			return new ResponseEntity<Response>(HttpStatus.UNAUTHORIZED);

		}

	}
	
//	@PostMapping("/authenticate")
//	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//		if (authentication.isAuthenticated()) {
//			return jwtService.generateToken(authRequest.getUsername());
//		} else {
//			throw new UsernameNotFoundException("Invalid User Credentials");
//		}
//
//	}

	@SuppressWarnings("unused")
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	@PostMapping(value = "/addEmployee")
	public ResponseEntity<EmployeeDomain> saveEmployee(@RequestBody EmployeeDomain employeeDomain) {
		return new ResponseEntity<>(service.addEmployee(employeeDomain), HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAllEmployees")
	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<EmployeeDomain>> getAllEmployees(
			@RequestParam(name = "offSet", defaultValue = "1") Integer offset,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer limit,
			@RequestParam(name = "Sortby") String Sortby) {
		return new ResponseEntity<>(service.getAllEmployees(offset, limit, Sortby), HttpStatus.OK);
	}

	@PutMapping(value = "/updateEmployee")
	public ResponseEntity<EmployeeDomain> updateEmployee(@RequestBody EmployeeDomain employeeDomain) {
		return new ResponseEntity<>(service.updateEmployee(employeeDomain), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/deleteEmplyee/{id}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable(value = "id") long id) {
		Boolean isDeleted = service.deleteEmployee(id);
		if (isDeleted == true) {
			Response response = new Response();
			response.setStatusType(StatusType.SUCCESS);
			response.setCode("200");
			response.setMessage("Employee is Inactive Successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			Response response = new Response();
			response.setStatusType(StatusType.ERROR);
			response.setCode("404");
			response.setMessage("Employee is Not Found");
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/getEmployee/{id}")
	public ResponseEntity<Response> getEmployeeById(@PathVariable(value = "id") Long id)
			throws ClassNotFoundException, IOException {

		EmployeeDomain getEmployee = service.getEmployeeById(id);
		if (getEmployee != null) {
			Response response = new Response();
			response.setStatusType(StatusType.SUCCESS);
			response.setCode("200");
			response.setMessage("Employee " + id + " is fetch Successfully");
			response.setData(getEmployee);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			Response response = new Response();
			response.setStatusType(StatusType.ERROR);
			response.setCode("404");
			response.setMessage("Employee " + id + " is Not Found");
			return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
		}

	}

	
}
