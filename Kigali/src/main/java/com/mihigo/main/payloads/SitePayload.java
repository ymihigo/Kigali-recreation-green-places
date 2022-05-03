package com.mihigo.main.payloads;

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

	public SitePayload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SitePayload(String email, String phone, String province, String district, String sector, String name,
			String status, double price, int id) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.name = name;
		this.status = status;
		this.price = price;
		this.id = id;
	}

	public SitePayload(String email, String phone, String province, String district, String sector, String name,
			String status, double price) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.name = name;
		this.status = status;
		this.price = price;
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

}
