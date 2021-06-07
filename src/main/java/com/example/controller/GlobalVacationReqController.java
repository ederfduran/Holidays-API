package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.VacationRequest;
import com.example.response.VacationRequestResponse;
import com.example.service.VacationRequestService;

@RestController
@RequestMapping("/v1/vacationrequests")
public class GlobalVacationReqController {
	
	@Autowired
	VacationRequestService vacationRequestService;
	
	@GetMapping("/")
	public List<VacationRequestResponse> getAllEmployeeVacationRequest(){
		// TODO Pagination
		List<VacationRequest> vacationRequest = vacationRequestService.getAllVacationRequests();
		List<VacationRequestResponse> vacationReqResponseList = new ArrayList<VacationRequestResponse>();
		vacationRequest.stream().forEach(vacRequest ->{
			vacationReqResponseList.add(new VacationRequestResponse(vacRequest));
		});
		return vacationReqResponseList;
	}

}
