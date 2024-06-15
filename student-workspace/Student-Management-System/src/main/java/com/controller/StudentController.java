package com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.StudentDomain;
import com.response.status.Response;
import com.response.status.StatusType;
import com.service.StudentAddressService;
import com.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	StudentAddressService studentAddressService;

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	@PostMapping("/newStudent")
	public ResponseEntity<StudentDomain> createStudent(@RequestBody  StudentDomain student) {
		return new ResponseEntity<>(studentService.createStudent(student),HttpStatus.CREATED);
	}

	@PutMapping("/updateStudent")
	public ResponseEntity<StudentDomain> update(@RequestBody  StudentDomain student) {
		return new ResponseEntity<>(studentService.updateStudent(student),HttpStatus.ACCEPTED);
	}

	@GetMapping("/getStudentDetail/{id}")
	public ResponseEntity<StudentDomain> fetchStudent(@PathVariable long id) {
		return new ResponseEntity<>(studentService.fetchStudent(id),HttpStatus.OK);

	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value ="/deleteStudent/{id}", produces = "application/json")
	public ResponseEntity<Response> DeleteById(@PathVariable long id) {
		boolean isDeleted =studentService.deleteStudent(id);
		if (isDeleted) {
			Response response = new Response();
			response.setStatusType(StatusType.SUCCESS);
			response.setCode("1");
			response.setMessage("Student is Deleted Successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			Response response = new Response();
			response.setStatusType(StatusType.FAILED);
			response.setCode("0");
			response.setMessage("Student is Deleted Failure");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<StudentDomain>> fetchall(
			@RequestParam(name = "offSet", defaultValue = "1") Integer offset,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer limit,
			@RequestParam(name = "Sortby") String Sortby) {
		return new ResponseEntity<>(studentService.fetchStudentDetails(offset, limit, Sortby),HttpStatus.OK);

	}

}
