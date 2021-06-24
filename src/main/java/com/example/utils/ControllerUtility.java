package com.example.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.entity.VacationRequest;
import com.example.entity.VacationRequest.Status;
import com.example.exceptions.InvalidQueryParameterException;
import com.example.response.VacationRequestResponse;

public class ControllerUtility {
	
	public static void validateStatus(String status) {
		boolean isValid = Arrays.stream(Status.class.getEnumConstants()).anyMatch(e -> e.name().equals(status));
		if (!status.equals("all") && !isValid)
			throw new InvalidQueryParameterException("status-"+ status + " not valid");
		
	}
	
	public static List<VacationRequestResponse> vacationRequestToVacationResponse(List<VacationRequest> vacationRequest){
		List<VacationRequestResponse> vacationReqResponseList = new ArrayList<VacationRequestResponse>();
		vacationRequest.stream().forEach(vacRequest ->{
			vacationReqResponseList.add(new VacationRequestResponse(vacRequest));
		});
		return vacationReqResponseList;
	}

}
