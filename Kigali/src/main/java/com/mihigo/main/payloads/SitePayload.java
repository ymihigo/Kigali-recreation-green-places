package com.mihigo.main.payloads;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SitePayload {
	private String email;
	private String phone;
	private String province;
	private String district;
	private String sector;
	private String name;
	private String status;
	private double price;
	private int id;
	private String ref;
	private String about;
	private String longitude;
	private String latitude;
	private List<String> photos;
	private boolean bookable;

	public SitePayload() {
		super();
	}

	public SitePayload(String email, String phone, String province, String district, String sector, String name,
			String status, double price, int id, String ref, boolean bookable) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.name = name;
		this.status = status;
		this.price = price;
		this.id = id;
		this.ref = ref;
		this.bookable = bookable;
	}

	public SitePayload(String email, String phone, String province, String district, String sector, String name,
			String status, double price, boolean bookable) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.name = name;
		this.status = status;
		this.price = price;
		this.bookable = bookable;
	}

	public SitePayload(String email, String phone, String province, String district, String sector, String name,
			String status, double price, String ref, boolean bookable) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.name = name;
		this.status = status;
		this.price = price;
		this.ref = ref;
		this.bookable = bookable;
	}

	public SitePayload(String email, String phone, String province, String district, String sector, String name,
			String status, double price, String ref, String about, String longitude, String latitude,
			List<String> photos, boolean bookable) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.name = name;
		this.status = status;
		this.price = price;
		this.ref = ref;
		this.about = about;
		this.longitude = longitude;
		this.latitude = latitude;
		this.photos = photos;
		this.bookable = bookable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
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
