package com.example.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVacationRequest {
	
	@JsonProperty("vacation_start_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message= "vacation_start_date is required")
	private Date vacationStartDate;
	
	@JsonProperty("vacation_end_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message= "vacation_end_date is required")
	private Date vacationEndDate;

}
