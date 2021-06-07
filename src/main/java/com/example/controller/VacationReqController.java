package com.example.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.example.entity.VacationRequest;
import com.example.request.CreateVacationRequest;
import com.example.request.UpdateVacationRequest;
import com.example.response.VacationRequestResponse;
import com.example.service.VacationRequestService;

@RestController
@RequestMapping("/v1/employees/{employeeId}/vacationrequests")
public class VacationReqController {
	
	@Autowired
	VacationRequestService vacationRequestService;
	
	@PostMapping("/")
	public VacationRequestResponse createVacationRequest(@Valid @RequestBody CreateVacationRequest createVacationRequest, @PathVariable Long employeeId) {
		VacationRequest vacationRequest = vacationRequestService.createVacationRequest(createVacationRequest, employeeId);
		return new VacationRequestResponse(vacationRequest);
	}
	
	@GetMapping("/")
	public List<VacationRequestResponse> getAllEmployeeVacationRequest(@PathVariable Long employeeId){
		List<VacationRequest> vacationRequest = vacationRequestService.getAllEmployeeVacationRequests(employeeId);
		List<VacationRequestResponse> vacationReqResponseList = new ArrayList<VacationRequestResponse>();
		vacationRequest.stream().forEach(vacRequest ->{
			vacationReqResponseList.add(new VacationRequestResponse(vacRequest));
		});
		return vacationReqResponseList;
	}
	
	@GetMapping("/{id}")
	public VacationRequestResponse getEmployeeVacationRequest(@PathVariable Long employeeId, @PathVariable Long id){
		VacationRequest vacationRequest = vacationRequestService.getEmployeeVacationRequest(employeeId, id);
		return new VacationRequestResponse(vacationRequest);
	}
	
	@PutMapping("/{id}")
	public VacationRequestResponse updateVacationRequest(@Valid @RequestBody UpdateVacationRequest updateVacationRequest, @PathVariable Long employeeId, @PathVariable Long id) {
		VacationRequest vacationRequest = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, id);
		return new VacationRequestResponse(vacationRequest);
	}
	
	@DeleteMapping("/{id}")
	public String deleteVacationRequest(@PathVariable Long employeeId, @PathVariable Long id) {
		return vacationRequestService.deleteEmployeeVacationRequest(employeeId, id);
	}
}
