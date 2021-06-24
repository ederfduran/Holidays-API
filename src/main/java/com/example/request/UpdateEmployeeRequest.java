package com.example.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeRequest {
		
	private String name;
	
	@JsonProperty("start_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@JsonProperty("vacation_days")
	private float vacationDays;
	
	@JsonProperty("vacation_days_taken")
	private float vacationDaysTaken;

}
