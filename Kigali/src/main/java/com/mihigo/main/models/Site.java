package com.mihigo.main.models;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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
	@JsonFormat(shape = Shape.STRING,pattern = "dd/MM/yyyy")
	private Date registrationDate = new Date();
	@Column(unique = true)
	private String refKey;
	@Column(columnDefinition = "TEXT")
	private String about;
	private String longitude;
	private String latitude;
	@ElementCollection
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private List<String> photos;
	@Type(type = "yes_no")
	private boolean bookable;

	public Site() {
		super();
	}

	public Site(String email, String phone, String province, String district, String sector) {
		super(email, phone, province, district, sector);
	}

	public Site(int id, String name, SiteStatus status, double price, Date registrationDate, String refKey,
			String about, String longitude, String latitude, List<String> photos, boolean bookable) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.price = price;
		this.registrationDate = registrationDate;
		this.refKey = refKey;
		this.about = about;
		this.longitude = longitude;
		this.latitude = latitude;
		this.photos = photos;
		this.bookable = bookable;
	}

	public Site(String email, String phone, String province, String district, String sector, String name,
			SiteStatus status, double price, String refKey, String about, String longitude, String latitude,
			boolean bookable) {
		super(email, phone, province, district, sector);
		this.name = name;
		this.status = status;
		this.price = price;
		this.refKey = refKey;
		this.about = about;
		this.longitude = longitude;
		this.latitude = latitude;
		this.bookable = bookable;
	}

	public Site(String name, SiteStatus status, double price, String refKey, String about, String longitude,
			String latitude, boolean bookable) {
		this.name = name;
		this.status = status;
		this.price = price;
		this.refKey = refKey;
		this.about = about;
		this.longitude = longitude;
		this.latitude = latitude;
		this.bookable = bookable;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public boolean isBookable() {
		return bookable;
	}

	public void setBookable(boolean bookable) {
		this.bookable = bookable;
	}

	
	
	

}
