package com.mihigo.main.service.site;

import java.io.IOException;
import java.util.ArrayList;
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
			String status, double price, String about, String longitude, String latitude,boolean bookable) {
		try {
			if (email.isEmpty() || phone.isEmpty() || province.isEmpty() || district.isEmpty() || sector.isEmpty()
					|| name.isEmpty() || status.isEmpty() || about.isEmpty() || longitude.isEmpty()
					|| latitude.isEmpty()) {
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

			return siterepo.save(new Site(email, phone, province, district, sector, name, ss, price, rand.random(16),
					about, longitude, latitude,bookable));

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
	public Site addSiteImages(String siteRefKey, List<MultipartFile> photos) {
		try {
			Site sit = searchByReferenceKey(siteRefKey);
			List<String> pics = new ArrayList<>();

			photos.forEach(x -> {
				String typ = x.getContentType();
				if (!typ.startsWith("image/")) {
					throw new RuntimeException("Only pictures are allowed here");
				}

				String img = StringUtils.cleanPath(x.getOriginalFilename());

				if (img.contains("..")) {
					throw new RuntimeException("Please select image");
				}

				try {
					String imagez = Base64.getEncoder().encodeToString(x.getBytes());
					pics.add(imagez);
				} catch (IOException e) {
					e.printStackTrace();
				}
				sit.setPhotos(pics);
			});
			return siterepo.saveAndFlush(sit);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
