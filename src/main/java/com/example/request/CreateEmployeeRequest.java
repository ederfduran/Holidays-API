package com.example.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequest {
	
	@NotBlank(message = "name required")
	private String name;
	
	@JsonProperty("start_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message= "startDate is required")
	private Date startDate;
	
}
