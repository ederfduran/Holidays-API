package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.VacationRequest;
import com.example.response.VacationRequestResponse;
import com.example.response.VacationRequestResponsePag;
import com.example.service.VacationRequestService;
import com.example.utils.ControllerUtility;

@RestController
@RequestMapping("/v1/vacationrequests")
public class GlobalVacationReqController {
	
	@Autowired
	VacationRequestService vacationRequestService;
	
	@GetMapping("")
	public VacationRequestResponsePag getAllEmployeeVacationRequest(
										@RequestParam(required = false, defaultValue = "10") int paginate,
										@RequestParam(required = false, defaultValue = "0") int page,
										@RequestParam(required = false, defaultValue = "all") String status,
										@RequestParam(required = false, defaultValue = "false") boolean overlap){
		
		ControllerUtility.validateStatus(status);
		List<VacationRequest> vacationRequest = vacationRequestService.getAllVacationRequests(page, paginate, status, overlap);
		List<VacationRequestResponse> vacationReqResponseList = ControllerUtility.vacationRequestToVacationResponse(vacationRequest);
		Integer nextPage = page+1;
		if (vacationReqResponseList.size() < paginate)
			nextPage = null;
		VacationRequestResponsePag response = new VacationRequestResponsePag(vacationReqResponseList, nextPage);
		return response; 
	}

}
