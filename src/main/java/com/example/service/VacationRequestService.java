package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.VacationRequest;
import com.example.repository.VacationRequestRepository;
import com.example.request.CreateVacationRequest;
import com.example.request.UpdateVacationRequest;

@Service
public class VacationRequestService {

	@Autowired
	VacationRequestRepository vacationRequestRepository;
	
	public VacationRequest createVacationRequest(CreateVacationRequest createVacationRequest, Long authorId) {
		VacationRequest vacationRequest = new VacationRequest(createVacationRequest);
		vacationRequest.setAuthor(authorId);
		return vacationRequestRepository.save(vacationRequest);
	}
	
	public VacationRequest updateEmployeeVacationRequest(UpdateVacationRequest updateVacationRequest, Long employeeId, Long id) {
		// TODO implement logic to search request under employeeId
		VacationRequest vacationRequest = vacationRequestRepository.findById(id).get();
		vacationRequest.updateVacationRequest(updateVacationRequest);
		return vacationRequestRepository.save(vacationRequest);
	}
	
	public VacationRequest getEmployeeVacationRequest(Long employeeId, Long id) {
		// TODO implement logic to search request under employeeId
		return vacationRequestRepository.findById(id).get();
	}
	
	
	public String deleteEmployeeVacationRequest(Long employeeId, Long id) {
		// TODO implement logic to search request under employeeId
		vacationRequestRepository.deleteById(id);
		return "Vacation Request has been deleted";
	}
	
	
	public List<VacationRequest> getAllEmployeeVacationRequests(Long employeeId){
		// TODO Implement logic to return all vacation requests by employee + pagination
		return vacationRequestRepository.findAll();
	}
	
	public List<VacationRequest> getAllVacationRequests(){
		// TODO Implement filtering by status and overlapping + pagination
		return vacationRequestRepository.findAll();
	}

}
