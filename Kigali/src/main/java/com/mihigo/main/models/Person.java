package com.mihigo.main.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import org.springframework.stereotype.Component;

@MappedSuperclass
@Component
public class Person extends Address {
	private String names;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	public Person() {
		super();
	}

	public Person(String names, Gender gender) {
		this.names = names;
		this.gender = gender;
	}

	public Person(String email, String phone, String province, String district, String sector) {
		super(email, phone, province, district, sector);
	}

	public Person(String email, String phone, String province, String district, String sector, String names,
			Gender gender) {
		super(email, phone, province, district, sector);
		this.names = names;
		this.gender = gender;
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

}
