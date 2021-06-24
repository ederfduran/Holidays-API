package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InconsistenDataException extends RuntimeException {

	public InconsistenDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
