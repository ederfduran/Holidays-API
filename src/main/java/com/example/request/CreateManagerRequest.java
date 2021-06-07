package com.example.request;


import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateManagerRequest {
	
	@NotBlank(message = "name required")
	private String name;
	
}
