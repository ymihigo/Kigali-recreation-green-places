package com.mihigo.main.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@SuppressWarnings("unused")
@Entity
public class BookingSite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date visitDateTime;
	@Type(type = "yes_no")
	private boolean came = false;
	@ManyToOne
	private Site site;
	@Column(unique = true)
	private String refKey;
	private int howMany;
	private String namez;
	private String phone;
	@Column(nullable = false)
	private String email;
	@Type(type = "yes_no")
	private boolean approved = false;

	public BookingSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingSite(long id, Date visitDateTime, boolean came, Site site, String refKey, int howMany, String namez,
			String phone, String email, boolean approved) {
		super();
		this.id = id;
		this.visitDateTime = visitDateTime;
		this.came = came;
		this.site = site;
		this.refKey = refKey;
		this.howMany = howMany;
		this.namez = namez;
		this.phone = phone;
		this.email = email;
		this.approved = approved;
	}

	public BookingSite(Date visitDateTime, Site site, String refKey, int howMany, String namez, String phone,
			String email) {
		super();
		this.visitDateTime = visitDateTime;
		this.site = site;
		this.refKey = refKey;
		this.howMany = howMany;
		this.namez = namez;
		this.phone = phone;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getVisitDateTime() {
		return visitDateTime;
	}

	public void setVisitDateTime(Date visitDateTime) {
		this.visitDateTime = visitDateTime;
	}

	public boolean isCame() {
		return came;
	}

	public void setCame(boolean came) {
		this.came = came;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}
