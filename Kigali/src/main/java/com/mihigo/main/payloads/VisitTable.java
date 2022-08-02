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
	private double amount;

	public VisitTable() {
		super();
	}

	public VisitTable(String names, Gender gender, Date date,double amount) {
		this.names = names;
		this.gender = gender;
		this.date = date;
		this.amount=amount;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
