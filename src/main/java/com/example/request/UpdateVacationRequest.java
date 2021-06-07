package com.example.request;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVacationRequest {
	
	
	//TODO Change to Enum
	private String status;
	
	private Long resolved_by;
	
	@JsonProperty("vacation_start_date")
	private Date vacationStartDate;
	
	@JsonProperty("vacation_end_date")
	private Date vacationEndDate;


}
