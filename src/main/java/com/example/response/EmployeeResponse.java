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
	 
	 @JsonProperty("vacation_days_taken")
	 private float vacationDaysTaken;
	 
	 @JsonProperty("vacation_days_available")
	private float vacationDaysAvailable;
	 
	 public EmployeeResponse(Employee employee) {
		 this.id = employee.getId();
		 this.name = employee.getName();
		 this.startDate = employee.getStartDate();
		 this.vacationDaysTaken = employee.getVacationDaysTaken();
		 this.vacationDaysAvailable = employee.getVacationDaysAvailable();
	 }
	 
}
