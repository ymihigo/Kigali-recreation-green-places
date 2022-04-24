package com.mihigo.main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date doneOn=new Date();
	@ManyToOne
	private Site site;
	@ManyToOne
	private Users uzer;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String doc;
	@Column(unique = true)
	private String refKey;
}
