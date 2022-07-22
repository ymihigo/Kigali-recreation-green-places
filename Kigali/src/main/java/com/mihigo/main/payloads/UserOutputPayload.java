package com.mihigo.main.payloads;

import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.mihigo.main.models.Gender;
import com.mihigo.main.models.Role;
import com.mihigo.main.models.UserStatus;

@Component
public class UserOutputPayload {
	private String email;
	private String phone;
	private String province;
	private String district;
	private String sector;
	private String names;
	private Gender gender;
	private Collection<Role> role;
	private String username;
	private String refKey;
	@JsonFormat(shape = Shape.STRING,pattern = "dd-MM-yyyy hh:mm")
	private Date date;
	private UserStatus status;

	public UserOutputPayload() {
		super();
	}

	public UserOutputPayload(String email, String phone, String province, String district, String sector, String names,
			Gender gender, Collection<Role> role, String username, String refKey, Date date, UserStatus status) {
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
		this.status = status;
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

	public Collection<Role> getRole() {
		return role;
	}

	public void setRole(Collection<Role> role) {
		this.role = role;
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

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

}
