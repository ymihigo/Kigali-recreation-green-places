package com.mihigo.main.models;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Districts implements Serializable {
	@Version
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String district;

	private int province_id;

	public Districts() {
	}

	public Districts(int id, String district, int province_id) {
		this.id = id;
		this.district = district;
		this.province_id = province_id;
	}

	public Districts(String district, int province_id) {
		this.district = district;
		this.province_id = province_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getprovince_id() {
		return province_id;
	}

	public void setprovince_id(int province_id) {
		this.province_id = province_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
