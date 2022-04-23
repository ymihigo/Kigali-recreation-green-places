package com.mihigo.main.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@Entity
public class Site extends Address implements Serializable {
	@Version
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private SiteStatus status;
	private double price = 0;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate = new Date();
//	@Lob
//	@Column(columnDefinition = "MEDIUMBLOB")
//	private List<String> images;

	public Site() {
		super();
	}

	public Site(String email, String phone, String province, String district, String sector) {
		super(email, phone, province, district, sector);
	}

	public Site(String email, String phone, String province, String district, String sector, int id, String name,
			SiteStatus status, double price, Date registrationDate) {
		super(email, phone, province, district, sector);
		this.id = id;
		this.name = name;
		this.status = status;
		this.price = price;
		this.registrationDate = registrationDate;
	}

	public Site(String email, String phone, String province, String district, String sector, String name,
			SiteStatus status, double price) {
		super(email, phone, province, district, sector);
		this.name = name;
		this.status = status;
		this.price = price;
	}

	public Site(String email, String phone, String province, String district, String sector, String name,
			SiteStatus status, double price, Date registrationDate) {
		super(email, phone, province, district, sector);
		this.name = name;
		this.status = status;
		this.price = price;
		this.registrationDate = registrationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SiteStatus getStatus() {
		return status;
	}

	public void setStatus(SiteStatus status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
