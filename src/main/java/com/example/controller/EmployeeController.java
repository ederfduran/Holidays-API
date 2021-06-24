package com.example.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.exceptions.EntityNotFoundException;
import com.example.request.CreateEmployeeRequest;
import com.example.request.UpdateEmployeeRequest;
import com.example.response.EmployeeResponse;
import com.example.service.EmployeeService;


@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("")
	public EmployeeResponse createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest) {
		Employee employee = employeeService.createEmployee(createEmployeeRequest);
		return new EmployeeResponse(employee);
	}
	
	@GetMapping("/{id}")
	public EmployeeResponse getEmployee(@PathVariable Long id){
		Employee employee = employeeService.getEmployee(id);
		if (employee == null) {
			throw new EntityNotFoundException("id-"+ id + " not found");
		}
		return new EmployeeResponse(employee);
	}
	
	@PutMapping("/{id}")
	public EmployeeResponse updateEmployee(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest, @PathVariable Long id) {
		Employee employee = employeeService.updateEmployee(updateEmployeeRequest, id);
		if (employee == null) {
			throw new EntityNotFoundException("id-"+ id + " not found");
		}
		return new EmployeeResponse(employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		String response = employeeService.deleteEmployee(id);
		if (response == null) {
			throw new EntityNotFoundException("id-"+ id + " not found");
		}
		return response;
	}

}
