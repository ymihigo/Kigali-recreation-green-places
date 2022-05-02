package com.mihigo.main.service.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.SiteStatus;
import com.mihigo.main.repositories.SiteRepository;
import com.mihigo.main.tools.Randomazation;

public class SiteInterfaceImplementation implements SiteInterface {

	@Autowired
	private SiteRepository siterepo;

	@Autowired
	private Randomazation rand;

	@Override
	public Site createSite(String email, String phone, String province, String district, String sector, String name,
			String status, double price) {
		try {
			if (email.isEmpty() || phone.isEmpty() || province.isEmpty() || district.isEmpty() || sector.isEmpty()
					|| name.isEmpty() || status.isEmpty()) {
				throw new RuntimeException("Please fill all requirements ");
			}
			if (price < 0) {
				throw new RuntimeException("Invalid price");
			}
			if (!phone.matches("^\\+2507[2-3-8-9]{1}\\d{7}$")) {
				throw new RuntimeException("Invalid phone number format");
			}
			if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
				throw new RuntimeException("Invalid email format");
			}
			SiteStatus ss = SiteStatus.valueOf(status);

			return siterepo.save(new Site(email, phone, province, district, sector, name, ss, price, rand.random(16)));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Site> allSite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site updateSite(int site_id, String email, String phone, String province, String district, String sector,
			String name, String status, double price) {
		// TODO Auto-generated method stub
		return null;
	}

}
