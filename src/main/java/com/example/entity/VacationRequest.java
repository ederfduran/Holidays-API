package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.request.CreateVacationRequest;
import com.example.request.UpdateVacationRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vacation_request")
public class VacationRequest extends EntityUtil{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	// TODO Add FK annotations
	@Column(name = "author")
	private Long author;
	
	// TODO Implement Enum
	@Column(name = "status")
	private String status;
	
	// TODO add FK annotations
	@Column(name = "resolved_by")
	private Long resolvedBy;
	
	@Column(name = "request_created_at")
	private Date requestCreatedAt;
	
	@Column(name = "vacation_start_date")
	private Date vacationStartDate;
	
	@Column(name = "vacation_end_date")
	private Date vacationEndDate;
	
	public VacationRequest(CreateVacationRequest createVacationRequest) {
		this.vacationStartDate = createVacationRequest.getVacationStartDate();
		this.vacationEndDate = createVacationRequest.getVacationEndDate();
		
		// TODO Change to Enum
		this.requestCreatedAt = new Date();
		this.status = "pending";
		this.resolvedBy = -1L;
		this.author = -1L;
	}
	
	public void updateVacationRequest(UpdateVacationRequest updateVacationRequest) {
		if (this.exists(updateVacationRequest.getStatus())) {
			this.setStatus(updateVacationRequest.getStatus());
		}
		if (this.exists(updateVacationRequest.getResolved_by())) {
			this.setResolvedBy(updateVacationRequest.getResolved_by());
		}
		if (this.exists(updateVacationRequest.getVacationStartDate())) {
			this.setVacationStartDate(updateVacationRequest.getVacationStartDate());
		}
		if (this.exists(updateVacationRequest.getVacationEndDate())) {
			this.setVacationEndDate(updateVacationRequest.getVacationEndDate());
		}
	}
}
