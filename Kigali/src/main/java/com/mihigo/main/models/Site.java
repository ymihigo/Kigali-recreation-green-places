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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.CollectionId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mihigo.main.tools.Randomazation;

@SuppressWarnings("unused")
@Component
@Entity
public class Site extends Address implements Serializable {
	@Version
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String name;
	@Enumerated(EnumType.STRING)
	private SiteStatus status;
	private double price = 0;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate = new Date();
	@Column(unique = true)
	private String refKey;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String photo_one;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String photo_two;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String photo_three;

	private String about;

	public Site() {
		super();
	}

	public Site(String email, String phone, String province, String district, String sector) {
		super(email, phone, province, district, sector);
	}

	public Site(int id, String name, SiteStatus status, double price, Date registrationDate, String refKey,
			String photo_one, String photo_two, String photo_three, String about) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.price = price;
		this.registrationDate = registrationDate;
		this.refKey = refKey;
		this.photo_one = photo_one;
		this.photo_two = photo_two;
		this.photo_three = photo_three;
		this.about = about;
	}

	public Site(String email, String phone, String province, String district, String sector, String name,
			SiteStatus status, double price, String refKey) {
		super(email, phone, province, district, sector);
		this.name = name;
		this.status = status;
		this.price = price;
		this.refKey = refKey;
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

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

	public String getPhoto_one() {
		return photo_one;
	}

	public void setPhoto_one(String photo_one) {
		this.photo_one = photo_one;
	}

	public String getPhoto_two() {
		return photo_two;
	}

	public void setPhoto_two(String photo_two) {
		this.photo_two = photo_two;
	}

	public String getPhoto_three() {
		return photo_three;
	}

	public void setPhoto_three(String photo_three) {
		this.photo_three = photo_three;
	}

}
