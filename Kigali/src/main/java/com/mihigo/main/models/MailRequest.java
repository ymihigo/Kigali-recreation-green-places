package com.mihigo.main.models;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
public class MailRequest {
	private String to;
	private String from;
	private String subject;

	public MailRequest() {
		super();
	}

	public MailRequest( String to, String from, String subject) {
		this.to = to;
		this.from = from;
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
