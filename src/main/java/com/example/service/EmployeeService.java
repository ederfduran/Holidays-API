package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.request.CreateEmployeeRequest;
import com.example.request.UpdateEmployeeRequest;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) {
		Employee employee = new Employee(createEmployeeRequest);
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest, Long id) {
		Employee employee = employeeRepository.findById(id).get();
		employee.updateEmployee(updateEmployeeRequest);
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).get();
	}
	
	public String deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return "Employee has been deleted";
	}

}
