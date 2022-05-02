package com.mihigo.main.payloads;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.mihigo.main.models.Gender;
import com.mihigo.main.models.UserRole;

@Component
public class UserOutputPayload {
	private int id;
	private String email;
	private String phone;
	private String province;
	private String district;
	private String sector;
	private String names;
	private Gender gender;
	private UserRole role;
	private int site;
	private String username;
	private String refKey;
	private Date date;

	public UserOutputPayload() {
		super();
	}

	public UserOutputPayload(int id, String email, String phone, String province, String district, String sector,
			String names, Gender gender, UserRole role, int site, String username, String refKey, Date date) {
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.names = names;
		this.gender = gender;
		this.role = role;
		this.site = site;
		this.username = username;
		this.refKey = refKey;
		this.date = date;
	}

	public UserOutputPayload(int id, String email, String phone, String province, String district, String sector,
			String names, Gender gender, UserRole role, String username, String refKey, Date date) {
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.names = names;
		this.gender = gender;
		this.role = role;
		this.username = username;
		this.refKey = refKey;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public int getSite() {
		return site;
	}

	public void setSite(int site) {
		this.site = site;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
