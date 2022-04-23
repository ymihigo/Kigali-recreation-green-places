package com.mihigo.main.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Sectors implements Serializable{
	@Version
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String sector;
	private int district_id;

	public Sectors() {
		super();
	}

	public Sectors(int id, String sector, int district_id) {
		this.id = id;
		this.sector = sector;
		this.district_id = district_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public int getdistrict_id() {
		return district_id;
	}

	public void setdistrict_id(int district_id) {
		this.district_id = district_id;
	}

}
