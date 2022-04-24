package com.mihigo.main.exceptionHandler;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ErrorDetail {
	private String details;
	private String message;
	private Date timeStamp;

	public ErrorDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorDetail(String details, String message, Date timeStamp) {
		this.details = details;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
