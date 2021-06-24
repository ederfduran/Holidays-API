package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public enum Status {
	    pending,
	    approved,
	    rejected
	  }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author")
	private Employee author;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resolved_by")	
	private Manager resolvedBy;
	
	@Column(name = "request_created_at")
	private Date requestCreatedAt;
	
	@Column(name = "vacation_start_date")
	private Date vacationStartDate;
	
	@Column(name = "vacation_end_date")
	private Date vacationEndDate;
	
	public VacationRequest(CreateVacationRequest createVacationRequest) {
		this.vacationStartDate = createVacationRequest.getVacationStartDate();
		this.vacationEndDate = createVacationRequest.getVacationEndDate();
		this.requestCreatedAt = new Date();
		this.status = Status.pending;
		this.resolvedBy = null;
		this.author = null;
	}
	
	public void updateVacationRequest(UpdateVacationRequest updateVacationRequest) {
		if (this.exists(updateVacationRequest.getStatus())) {
			this.setStatus(Status.valueOf(updateVacationRequest.getStatus()));
		} 
		if (this.exists(updateVacationRequest.getVacationStartDate())) {
			this.setVacationStartDate(updateVacationRequest.getVacationStartDate());
		}
		if (this.exists(updateVacationRequest.getVacationEndDate())) {
			this.setVacationEndDate(updateVacationRequest.getVacationEndDate());
		}
	}
}
