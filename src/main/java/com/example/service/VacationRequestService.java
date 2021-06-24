package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.entity.Manager;
import com.example.entity.VacationRequest;
import com.example.entity.VacationRequest.Status;
import com.example.repository.EmployeeRepository;
import com.example.repository.ManagerRepository;
import com.example.repository.VacationRequestRepository;
import com.example.request.CreateVacationRequest;
import com.example.request.UpdateVacationRequest;

import lombok.Setter;

@Setter
@Service
public class VacationRequestService {

	@Autowired
	VacationRequestRepository vacationRequestRepository;
		
	@Autowired
	ManagerRepository managerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	private int getDaysRequested(Date startDate, Date endDate) {
		// TODO consider only week days
		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	    long differenceInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return (int)differenceInDays;
	}
	
	private Manager getManagerById(Long id) {
		Optional<Manager> manager = managerRepository.findById(id);
		if (manager.isEmpty()) {
			return null;
		}
		return manager.get();
	}
	
	private Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty()) {
			return null;
		}
		return employee.get();
	}
	
	private Pair<VacationRequest, String> validateAndSaveVacationRequest(VacationRequest vacationRequest, Employee employee){
		int daysRequested = this.getDaysRequested(vacationRequest.getVacationStartDate(), vacationRequest.getVacationEndDate());
		int daysAvailable = (int) employee.getVacationDaysAvailable();
		if (daysRequested < daysAvailable) {
			if (vacationRequest.getStatus().equals(Status.approved))
				employee.setVacationDaysTaken(daysRequested);
			vacationRequest.setAuthor(employee);
			return new Pair<VacationRequest, String>(vacationRequestRepository.save(vacationRequest) , null);
		} else {
			return  new Pair<VacationRequest, String>(null, "No Vacations available");
		}
	}
	
	public Pair<VacationRequest, String> createVacationRequest(CreateVacationRequest createVacationRequest, Long authorId) {
		VacationRequest vacationRequest = new VacationRequest(createVacationRequest);
		Employee employee = this.getEmployeeById(authorId);
		if (employee == null)
			return  new Pair<VacationRequest, String>(null, "authorID invalid "+ authorId);
		return this.validateAndSaveVacationRequest(vacationRequest, employee);
		
	}
	
	public Pair<VacationRequest, String> updateEmployeeVacationRequest(UpdateVacationRequest updateVacationRequest, Long employeeId, Long id) { 
		Employee employee = this.getEmployeeById(employeeId);
		if (employee == null) {
			return  new Pair<VacationRequest, String>(null, "EmployeeId invalid "+ employeeId);
		}
		VacationRequest vacationRequest = vacationRequestRepository.findByAuthorAndId(employee, id);
		if (vacationRequest == null)
			return  new Pair<VacationRequest, String>(null, "Vacation RequestId invalid " + id);
		vacationRequest.updateVacationRequest(updateVacationRequest);
		
		boolean isValid = vacationRequest.getVacationStartDate().before(vacationRequest.getVacationEndDate());
		if (!isValid)
			return new Pair<VacationRequest, String>(null, "Iconsistent dates");
		
		Long resolvedBy = updateVacationRequest.getResolvedBy();
		if (resolvedBy != null) {
			Manager manager = this.getManagerById(resolvedBy);
			if (manager != null)
				vacationRequest.setResolvedBy(manager);
			else
				return new Pair<VacationRequest, String>(null, "resolvedBy invalid "+  resolvedBy);
		}
		if (updateVacationRequest.getVacationStartDate() != null || updateVacationRequest.getVacationEndDate() == null)
			return this.validateAndSaveVacationRequest(vacationRequest, employee);
		return new Pair<VacationRequest, String>(vacationRequestRepository.save(vacationRequest), null);
	}
	
	public VacationRequest getEmployeeVacationRequest(Long employeeId, Long id) {
		Employee employee = this.getEmployeeById(employeeId);
		if (employee == null)
			return null;
		VacationRequest vacationRequest = vacationRequestRepository.findByAuthorAndId(employee, id);
		return vacationRequest;
	}
	
	
	public String deleteEmployeeVacationRequest(Long employeeId, Long id) {
		// TODO implement logic to search request under employeeId
		Employee employee = this.getEmployeeById(employeeId);
		if (employee == null)
			return null;
		vacationRequestRepository.deleteByAuthorAndId(employee, id);
		return "Vacation Request has been deleted";
	}
	
	
	public List<VacationRequest> getAllEmployeeVacationRequests(Long employeeId, String status){
		Employee employee = this.getEmployeeById(employeeId);
		if (employee == null)
			return null;
		
		if (status.equals("all"))
			return vacationRequestRepository.findByAuthor(employee);
		Status reqStatus = Status.valueOf(status);
		return vacationRequestRepository.findByAuthorAndStatus(employee, reqStatus);
	}
	
	public List<VacationRequest> getAllVacationRequests(int page, int pageSize, String status, boolean overlap){
		// TODO overlapping 
		Pageable pageable = PageRequest.of(page, pageSize);
		if (status.equals("all"))
			if (overlap)
				return vacationRequestRepository.getOverlapVacationRequests(pageable);
			else
				return vacationRequestRepository.findAll(pageable).getContent();
		Status reqStatus = Status.valueOf(status);
		if (overlap)
			return vacationRequestRepository.getOverlapVacationRequestsByStatus(status, pageable);
		else
			return vacationRequestRepository.findByStatus(reqStatus, pageable);
		
	}

}
