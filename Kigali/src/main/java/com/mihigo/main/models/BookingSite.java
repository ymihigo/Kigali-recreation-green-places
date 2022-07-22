package com.mihigo.main.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@SuppressWarnings("unused")
@Entity
public class BookingSite extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private LocalDateTime visitDateTime = LocalDateTime.now();
	private boolean came = false;
	@ManyToOne
	private Site site;
	private String refKey;

	public BookingSite() {
		super();
	}

	public BookingSite(String email, String phone, String province, String district, String sector, String names,
			Gender gender, long id, LocalDateTime visitDateTime, boolean came, Site site, String refKey) {
		super(email, phone, province, district, sector, names, gender);
		this.id = id;
		this.visitDateTime = visitDateTime;
		this.came = came;
		this.site = site;
		this.refKey = refKey;
	}

	public BookingSite(String email, String phone, String province, String district, String sector, String names,
			Gender gender, Site site) {
		super(email, phone, province, district, sector, names, gender);
		this.site = site;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getVisitDateTime() {
		return visitDateTime;
	}

	public void setVisitDateTime(LocalDateTime visitDateTime) {
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
}
