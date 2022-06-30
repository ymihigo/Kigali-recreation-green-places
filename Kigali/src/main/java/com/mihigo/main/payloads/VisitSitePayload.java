package com.mihigo.main.payloads;

public class VisitSitePayload {
	private String email;
	private String phone;
	private String province;
	private String district;
	private String sector;
	private String names;
	private String gender;
	private String siteRef;
	private double paidAmount;

	public VisitSitePayload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VisitSitePayload(String email, String phone, String province, String district, String sector, String names,
			String gender, String siteRef, double paidAmount) {
		this.email = email;
		this.phone = phone;
		this.province = province;
		this.district = district;
		this.sector = sector;
		this.names = names;
		this.gender = gender;
		this.siteRef = siteRef;
		this.paidAmount = paidAmount;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSiteRef() {
		return siteRef;
	}

	public void setSiteRef(String siteRef) {
		this.siteRef = siteRef;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

}
