package com.mihigo.main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = true)
	private String email;
	@Column(nullable = true)
	private String name;
	@Column(nullable = false,updatable = false)
	private String questionz;
	@Column(nullable = true)
	private String answerz;
	@ManyToOne
	private Site site;
	@Column(unique = true, nullable = false)
	private String refKey;
	@Type(type = "yes_no")
	private boolean readz = false;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyy hh:mm")
	private Date datez = new Date();

}
