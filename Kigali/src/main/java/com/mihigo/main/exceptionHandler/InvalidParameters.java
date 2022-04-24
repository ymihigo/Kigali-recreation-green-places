package com.mihigo.main.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParameters extends Exception {

	private static final long serialVersionUID = 4787668878226381858L;

	public InvalidParameters(String message) {
		super(message);
	}

}
