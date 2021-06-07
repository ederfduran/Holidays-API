package com.example.request;


import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateManagerRequest {
		
	@NotNull(message= "Name is required")
	private String name;
	
}
