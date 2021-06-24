package com.example.service;

import java.util.Optional;

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
	
	private Employee getById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty()) {
			return null;
		}
		return employee.get();
	}
	
	public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) {
		Employee employee = new Employee(createEmployeeRequest);
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest, Long id) {
		Employee employee = this.getById(id);
		if (employee == null)
			return null;
		employee.updateEmployee(updateEmployeeRequest);
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployee(Long id) {
		return this.getById(id);
	}
	
	public String deleteEmployee(Long id) {
		Employee employee = this.getById(id);
		if (employee == null)
			return null;
		employeeRepository.deleteById(id);
		return "Employee has been deleted";
	}

}
