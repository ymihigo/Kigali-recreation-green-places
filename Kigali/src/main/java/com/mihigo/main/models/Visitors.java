package com.mihigo.main.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Visitors extends Person implements Serializable {
	@Version
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Site site;
	private double paidAmount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();

	public Visitors() {
		super();
	}

	public Visitors(String email, String phone, String province, String district, String sector, String names,
			Gender gender) {
		super(email, phone, province, district, sector, names, gender);
	}

	public Visitors(String email, String phone, String province, String district, String sector) {
		super(email, phone, province, district, sector);
	}

	public Visitors(String email, String phone, String province, String district, String sector, String names,
			Gender gender, long id, Site site, double paidAmount, Date date) {
		super(email, phone, province, district, sector, names, gender);
		this.id = id;
		this.site = site;
		this.paidAmount = paidAmount;
		this.date = date;
	}

	public Visitors(String email, String phone, String province, String district, String sector, String names,
			Gender gender, Site site, double paidAmount) {
		super(email, phone, province, district, sector, names, gender);
		this.site = site;
		this.paidAmount = paidAmount;
	}
	
	

	public Visitors(Site site, double paidAmount) {
		this.site = site;
		this.paidAmount = paidAmount;
	}

	public Visitors(String names, Gender gender, Site site, double paidAmount) {
		super(names, gender);
		this.site = site;
		this.paidAmount = paidAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
