package com.mihigo.main.payloads;

import java.util.Date;

import javax.persistence.Lob;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
@Component
public class ReportPayload {
	private UserOutputPayload uzer;
	private SitePayload site;
	private String detail;
	@Lob
	private String doc;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date doneOn;
	private String refKey;

	public ReportPayload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportPayload(UserOutputPayload uzer, SitePayload site, String detail, String doc, Date doneOn,
			String refKey) {
		this.uzer = uzer;
		this.site = site;
		this.detail = detail;
		this.doc = doc;
		this.doneOn = doneOn;
		this.refKey = refKey;
	}

	public UserOutputPayload getUzer() {
		return uzer;
	}

	public void setUzer(UserOutputPayload uzer) {
		this.uzer = uzer;
	}

	public SitePayload getSite() {
		return site;
	}

	public void setSite(SitePayload site) {
		this.site = site;
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

	public Date getDoneOn() {
		return doneOn;
	}

	public void setDoneOn(Date doneOn) {
		this.doneOn = doneOn;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}
	
}
