package com.mihigo.main.service.site;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.SiteStatus;
import com.mihigo.main.models.Users;
import com.mihigo.main.repositories.SiteRepository;
import com.mihigo.main.repositories.UserRepository;
import com.mihigo.main.tools.Randomazation;

@Service
public class SiteInterfaceImplementation implements SiteInterface {

	@Autowired
	private SiteRepository siterepo;

	@Autowired
	private UserRepository usr;

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
		try {
			return siterepo.findAll();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Site updateSite(String refKey, String email, String phone, String province, String district, String sector,
			String name, String status, double price, String about) {
		try {
			return null;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Site searchByReferenceKey(String refKey) {
		try {
			if (refKey.isEmpty()) {
				throw new RuntimeException("reference key can not be null");
			}
			Site s = siterepo.searchByrefKey(refKey);

			if (s == null) {
				throw new RuntimeException("no site found");
			}
			return s;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Users> getUsersBySiteRefKey(String refKey) {
		try {
			if (refKey.isEmpty()) {
				throw new RuntimeException("Invalid ref key");
			}
			List<Users> lu = usr.allUserBySiteRefKey(refKey);
			return lu;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Site> findByStatus(SiteStatus status) {
		try {
			return siterepo.findAllByStatus(status);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public long countByStatus(SiteStatus status) {
		try {
			return siterepo.countAllByStatus(status);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public long countAllSites() {
		try {
			return siterepo.count();
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	@Override
	public Site addSiteImages(String siteRefKey, MultipartFile photo1, MultipartFile photo2, MultipartFile photo3) {
		try {

			String typ = photo1.getContentType();
			String typ2 = photo2.getContentType();
			String typ3 = photo3.getContentType();
			if (typ.startsWith("image/") && typ2.startsWith("image/") && typ3.startsWith("image/")) {
				String img1 = StringUtils.cleanPath(photo1.getOriginalFilename());
				String img2 = StringUtils.cleanPath(photo2.getOriginalFilename());
				String img3 = StringUtils.cleanPath(photo3.getOriginalFilename());

				if (img1.contains("..") || img2.contains("..") || img3.contains("..")) {
					throw new RuntimeException("All images are required");
				}

				Site sit = searchByReferenceKey(siteRefKey);

				String image1 = Base64.getEncoder().encodeToString(photo1.getBytes());
				String image2 = Base64.getEncoder().encodeToString(photo2.getBytes());
				String image3 = Base64.getEncoder().encodeToString(photo3.getBytes());

				sit.setPhoto_one(image1);
				sit.setPhoto_two(image2);
				sit.setPhoto_three(image3);

				return siterepo.saveAndFlush(sit);
			} else {
				throw new RuntimeException("Invalid file type");
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
