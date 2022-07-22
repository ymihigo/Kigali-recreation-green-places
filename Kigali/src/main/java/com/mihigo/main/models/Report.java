package com.mihigo.main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date doneOn =new Date();
	@ManyToOne
	private Users uzer;
	@Column(length = 1000)
	private String detail;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String doc;
	@Column(unique = true)
	private String refKey;

	public Report() {
		super();
	}

	public Report(long id, Date doneOn, Users uzer, String detail, String doc, String refKey) {
		this.id = id;
		this.doneOn = doneOn;
		this.uzer = uzer;
		this.detail = detail;
		this.doc = doc;
		this.refKey = refKey;
	}

	public Report(Users uzer, String detail, String refKey) {
		this.uzer = uzer;
		this.detail = detail;
		this.refKey = refKey;
	}

	public Report(Users uzer, String detail, String doc, String refKey) {
		this.uzer = uzer;
		this.detail = detail;
		this.doc = doc;
		this.refKey = refKey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDoneOn() {
		return doneOn;
	}

	public void setDoneOn(Date doneOn) {
		this.doneOn = doneOn;
	}

	public Users getUzer() {
		return uzer;
	}

	public void setUzer(Users uzer) {
		this.uzer = uzer;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

}
