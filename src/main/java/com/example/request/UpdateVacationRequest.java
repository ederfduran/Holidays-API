package com.example.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVacationRequest {
	
	private String status;
	
	@JsonProperty("resolved_by")
	private Long resolvedBy;
	
	@JsonProperty("vacation_start_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vacationStartDate;
	
	@JsonProperty("vacation_end_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vacationEndDate;


}
