package com.mihigo.main.models;

import javax.persistence.MappedSuperclass;

import org.springframework.stereotype.Component;


@Component
@MappedSuperclass
public class Address {
	private String email;
	private String phone;
	private String province;
	private String district;
	private String sector;

	public Address() {
		super();
	}

	public Address(String email, String phone, String province, String district, String sector) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
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

}
