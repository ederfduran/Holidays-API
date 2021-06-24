package com.example.controller;


import java.util.List;

import javax.validation.Valid;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.VacationRequest;
import com.example.entity.VacationRequest.Status;
import com.example.exceptions.EntityNotFoundException;
import com.example.exceptions.InconsistenDataException;
import com.example.request.CreateVacationRequest;
import com.example.request.UpdateVacationRequest;
import com.example.response.VacationRequestResponse;
import com.example.service.VacationRequestService;
import com.example.utils.ControllerUtility;

@RestController
@RequestMapping("/v1/employees/{employeeId}/vacationrequests")
public class VacationReqController {
	
	@Autowired
	VacationRequestService vacationRequestService;
	
	@PostMapping("")
	public VacationRequestResponse createVacationRequest(@Valid @RequestBody CreateVacationRequest createVacationRequest, @PathVariable Long employeeId) {
		boolean isValid = createVacationRequest.getVacationStartDate().before(createVacationRequest.getVacationEndDate());
		if (!isValid)
			throw new InconsistenDataException("Iconsistent dates");
		Pair<VacationRequest, String> response = vacationRequestService.createVacationRequest(createVacationRequest, employeeId);
		VacationRequest vacationRequest = response.getValue0();
		if (vacationRequest == null)
			throw new EntityNotFoundException(response.getValue1());
		return new VacationRequestResponse(vacationRequest);
	}
	
	@GetMapping("")
	public List<VacationRequestResponse> getAllEmployeeVacationRequest(
																	@PathVariable Long employeeId,
																	@RequestParam(required = false, defaultValue = "all") String status){
		ControllerUtility.validateStatus(status);
		List<VacationRequest> vacationRequest = vacationRequestService.getAllEmployeeVacationRequests(employeeId, status);
		if (vacationRequest == null)
			throw new EntityNotFoundException("id-"+ employeeId + " not found");
		return ControllerUtility.vacationRequestToVacationResponse(vacationRequest);
	}
	
	@GetMapping("/{id}")
	public VacationRequestResponse getEmployeeVacationRequest(@PathVariable Long employeeId, @PathVariable Long id){
		VacationRequest vacationRequest = vacationRequestService.getEmployeeVacationRequest(employeeId, id);
		if (vacationRequest == null)
			throw new EntityNotFoundException("employeeId-"+ employeeId + " id-"+ id +" not found");
		return new VacationRequestResponse(vacationRequest);
	}
	
	@PutMapping("/{id}")
	public VacationRequestResponse updateVacationRequest(@Valid @RequestBody UpdateVacationRequest updateVacationRequest, @PathVariable Long employeeId, @PathVariable Long id) {
		String status = updateVacationRequest.getStatus();
		if (status != null) {
			if (!status.equals(Status.pending.name()) && updateVacationRequest.getResolvedBy() == null)
				throw new InconsistenDataException("resolved_by must be supplied");
			ControllerUtility.validateStatus(status);
		}
		Pair<VacationRequest, String>  response = vacationRequestService.updateEmployeeVacationRequest(updateVacationRequest, employeeId, id);
		VacationRequest vacationRequest = response.getValue0();
		if (vacationRequest == null)
			throw new InconsistenDataException(response.getValue1());
		return new VacationRequestResponse(vacationRequest);
	}
	
	@DeleteMapping("/{id}")
	public String deleteVacationRequest(@PathVariable Long employeeId, @PathVariable Long id) {
		String response = vacationRequestService.deleteEmployeeVacationRequest(employeeId, id);
		if (response == null)
			throw new EntityNotFoundException("employeeId-"+ employeeId + " id-"+ id +" not found");
		return response;
	}
}
