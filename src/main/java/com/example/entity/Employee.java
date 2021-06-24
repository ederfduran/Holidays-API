package com.example.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.request.CreateEmployeeRequest;
import com.example.request.UpdateEmployeeRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends EntityUtil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "start_date")
	private Date startDate;
	
	
	@Column(name = "vacation_days_taken")
	private float vacationDaysTaken;
	
	
	public float getVacationDaysAvailable() {
		Date now = new Date();
		long diffInMillies = Math.abs(this.startDate.getTime() - now.getTime());
	    long differenceInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return ((differenceInDays * 30)/365) - this.vacationDaysTaken;
	}
	
	public Employee(CreateEmployeeRequest createEmployeeRequest) {
		this.name = createEmployeeRequest.getName();
		this.startDate = createEmployeeRequest.getStartDate();
		this.vacationDaysTaken = 0.0f;
	}
	
	public void updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) {
		if (this.exists(updateEmployeeRequest.getName())) {
			this.setName(updateEmployeeRequest.getName());
		}
		if (this.exists(updateEmployeeRequest.getVacationDaysTaken())) {
			this.setVacationDaysTaken(updateEmployeeRequest.getVacationDaysTaken());
		}
		if (this.exists(updateEmployeeRequest.getStartDate())) {
			this.setStartDate(updateEmployeeRequest.getStartDate());
		}
		
	}

}
