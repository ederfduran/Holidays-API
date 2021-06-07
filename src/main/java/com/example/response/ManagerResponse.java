package com.example.response;

import com.example.entity.Manager;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponse {
	
	private Long id;
	
	private String name;
	
	public ManagerResponse(Manager manager) {
		this.id = manager.getId();
		this.name = manager.getName();
	}	
}
