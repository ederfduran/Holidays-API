package com.example.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationRequestResponsePag {
	
	@JsonProperty("vacations_requests")
	private List<VacationRequestResponse> vacationRequestResponse;
	
	@JsonProperty("next_page")
	private Integer nextPageNum;

	
}
