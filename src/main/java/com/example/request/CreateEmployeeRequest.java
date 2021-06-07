package com.example.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequest {
	
	@NotBlank(message = "name required")
	private String name;
	
	@JsonProperty("start_date")
	@NotBlank(message = "start date required")
	private Date startDate;
	
}
