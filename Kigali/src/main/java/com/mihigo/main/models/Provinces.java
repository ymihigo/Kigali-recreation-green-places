package com.mihigo.main.models;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Provinces implements Serializable{
	@Version
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String province;

	public Provinces() {
		super();
	}

	public Provinces(int id, String province) {
		this.id = id;
		this.province = province;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
