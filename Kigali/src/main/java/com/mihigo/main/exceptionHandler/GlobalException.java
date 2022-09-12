package com.mihigo.main.exceptionHandler;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@SuppressWarnings("unused")
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(InvalidParameters.class)
	public ResponseEntity<?> invalidParameterExceptionHandling(InvalidParameters exception, WebRequest request) {
		ErrorDetail error = new ErrorDetail(request.getDescription(false), exception.getMessage(), new Date());
		return new ResponseEntity<ErrorDetail>(error, HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> logicError(InvalidParameters exception, WebRequest request) {
		ErrorDetail error = new ErrorDetail(request.getDescription(false), exception.getMessage(), new Date());
		return new ResponseEntity<ErrorDetail>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> globalException(Exception exception, WebRequest request) {
		ErrorDetail error = new ErrorDetail(request.getDescription(false), exception.getMessage(), new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
