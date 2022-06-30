package com.mihigo.main.payloads;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mihigo.main.models.Gender;

@Component
public class VisitTable {
	private String names;
	private Gender gender;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm")
	private Date date;

	public VisitTable() {
		super();
	}

	public VisitTable(String names, Gender gender, Date date) {
		this.names = names;
		this.gender = gender;
		this.date = date;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
