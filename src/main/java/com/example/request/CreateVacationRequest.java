package com.example.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVacationRequest {
	
	@JsonProperty("vacation_start_date")
	@NotBlank(message = "vacation start date required")
	private Date vacationStartDate;
	
	@JsonProperty("vacation_end_date")
	@NotBlank(message = "vacation end date required")
	private Date vacationEndDate;

}
