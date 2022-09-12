package com.mihigo.main.payloads;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.mihigo.main.models.Site;

@Component
public class BookingPayload {
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date visitDateTime;
	private String refKey;
	private int howMany;
	private String namez;
	private String phone;
	private String email;

	public BookingPayload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingPayload(Date visitDateTime, String refKey, int howMany, String namez, String phone, String email) {
		super();
		this.visitDateTime = visitDateTime;
		this.refKey = refKey;
		this.howMany = howMany;
		this.namez = namez;
		this.phone = phone;
		this.email = email;
	}

	public Date getVisitDateTime() {
		return visitDateTime;
	}

	public void setVisitDateTime(Date visitDateTime) {
		this.visitDateTime = visitDateTime;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

	public int getHowMany() {
		return howMany;
	}

	public void setHowMany(int howMany) {
		this.howMany = howMany;
	}

	public String getNamez() {
		return namez;
	}

	public void setNamez(String namez) {
		this.namez = namez;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
