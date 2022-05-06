package com.mihigo.main.service.site;

import java.util.List;

import com.mihigo.main.models.Site;

public interface SiteInterface {

	Site createSite(String email, String phone, String province, String district, String sector, String name,
			String status, double price);

	List<Site> allSite();

	Site updateSite(int site_id, String email, String phone, String province, String district, String sector,
			String name, String status, double price);

	Site searchByReferenceKey(String refKey);

}
