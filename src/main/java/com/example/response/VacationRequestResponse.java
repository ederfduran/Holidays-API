package com.example.response;

import java.util.Date;

import com.example.entity.VacationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationRequestResponse {
	
	private Long id;
	
	private Long author;
	
	private String status;
	
	@JsonProperty("resolved_by")
	private Long resolvedBy;
	
	@JsonProperty("request_created_at")
	private Date requestCreatedAt;
	
	@JsonProperty("vacation_start_date")
	private Date vacationStartDate;
	
	@JsonProperty("vacation_end_date")
	private Date vacationEndDate;
	
	public VacationRequestResponse(VacationRequest vacationRequest) {
		this.id = vacationRequest.getId();
		this.author = vacationRequest.getAuthor().getId();
		this.status = vacationRequest.getStatus().name();
		if (vacationRequest.getResolvedBy() != null)
			this.resolvedBy = vacationRequest.getResolvedBy().getId();
		this.requestCreatedAt = vacationRequest.getRequestCreatedAt();
		this.vacationStartDate = vacationRequest.getVacationStartDate();
		this.vacationEndDate = vacationRequest.getVacationEndDate();
	}

}
