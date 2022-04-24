package com.mihigo.main.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CollectionId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mihigo.main.tools.Randomazation;

@SuppressWarnings("unused")
@Component
@Entity
public class Site extends Address implements Serializable {
	@Version
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private SiteStatus status;
	private double price = 0;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate = new Date();
	@Column(unique = true)
	private String refKey;

}
