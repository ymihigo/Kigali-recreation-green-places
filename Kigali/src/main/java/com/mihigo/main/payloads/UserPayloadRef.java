package com.mihigo.main.payloads;

import org.springframework.stereotype.Component;

@Component
public class UserPayloadRef {
	private String email;
	private String phone;
	private String province;
	private String district;
	private String sector;
	private String names;
	private String gender;
	private String role;
	private String siteRefKey;
	private String username;
	private String password;

	public UserPayloadRef() {
		super();
	}

	public UserPayloadRef(String email, String phone, String province, String district, String sector, String names,
			String gender, String role, String siteRefKey, String username, String password) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.names = names;
		this.gender = gender;
		this.role = role;
		this.siteRefKey = siteRefKey;
		this.username = username;
		this.password = password;
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

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSiteRefKey() {
		return siteRefKey;
	}

	public void setSiteRefKey(String siteRefKey) {
		this.siteRefKey = siteRefKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
