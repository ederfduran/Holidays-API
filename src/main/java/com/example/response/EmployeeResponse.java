package com.example.response;

import java.util.Date;

import com.example.entity.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
	
	 private Long id;
	 
	 private String name;
	 
	 @JsonProperty("start_date")
	 private Date startDate;
	 
	 @JsonProperty("vacation_days")
	 private float vacationDays;
	 
	 @JsonProperty("vacation_days_taken")
	 private float vacationDaysTaken;
	 
	 public EmployeeResponse(Employee employee) {
		 this.id = employee.getId();
		 this.name = employee.getName();
		 this.startDate = employee.getStartDate();
		 this.vacationDays = employee.getVacationDays();
		 this.vacationDaysTaken = employee.getVacationDaysTaken();
	 }
	 
}
