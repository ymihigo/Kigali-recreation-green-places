package com.mihigo.main.service.site;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.SiteStatus;
import com.mihigo.main.models.Users;

public interface SiteInterface {

	Site createSite(String email, String phone, String province, String district, String sector, String name,
			String status, double price,String about,String longitute,String latitude,boolean bookable);

	List<Site> allSite();

	Site updateSite(String refKey, String email, String phone, String province, String district, String sector,
			String name, String status, double price, String about);

	Site searchByReferenceKey(String refKey);

	List<Users> getUsersBySiteRefKey(String refKey);

	List<Site> findByStatus(SiteStatus status);

	long countByStatus(SiteStatus status);

	long countAllSites();
	
	Site addSiteImages(String siteRefKey, List<MultipartFile> photos);

}
