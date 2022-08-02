package com.mihigo.main.payloads;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class TopVisitings {
	private String name;
	private long visit_number;
	private double revenue;

	public TopVisitings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopVisitings(String name, long visit_number, double revenue) {
		this.name = name;
		this.visit_number = visit_number;
		this.revenue = revenue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getVisit_number() {
		return visit_number;
	}

	public void setVisit_number(long visit_number) {
		this.visit_number = visit_number;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
}
