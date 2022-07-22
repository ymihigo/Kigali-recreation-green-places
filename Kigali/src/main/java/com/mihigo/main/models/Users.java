package com.mihigo.main.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Users extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.Active;
	@ManyToOne
	private Site site;
	@Column(unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();
	@Column(unique = true)
	private String refKey;

	public Users() {
		super();
	}

	// Register Admin
	public Users(String email, String phone, String province, String district, String sector, String names,
			Gender gender, Collection<Role> role, String username, String password, String refKey) {
		super(email, phone, province, district, sector, names, gender);
		this.roles = role;
		this.username = username;
		this.password = password;
		this.refKey = refKey;
	}

	// Register Managers
	public Users(String email, String phone, String province, String district, String sector, String names,
			Gender gender, Collection<Role> role, Site site, String username, String password, String refKey) {
		super(email, phone, province, district, sector, names, gender);
		this.roles = role;
		this.site = site;
		this.username = username;
		this.password = password;
		this.refKey = refKey;
	}

//	for update and upload image
	public Users(String email, String phone, String province, String district, String sector, String names,
			Gender gender, int id, Collection<Role> role, String image, UserStatus status, Site site,
			String username, String password, String refKey) {
		super(email, phone, province, district, sector, names, gender);
		this.id = id;
		this.roles = role;
		this.image = image;
		this.status = status;
		this.site = site;
		this.username = username;
		this.password = password;
		this.refKey = refKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Role> getRole() {
		return roles;
	}

	public void setRole(Collection<Role> role) {
		this.roles = role;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRefKey() {
		return refKey;
	}

	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}

}
