package com.mihigo.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mihigo.main.exceptionHandler.InvalidParameters;
import com.mihigo.main.models.Districts;
import com.mihigo.main.models.Gender;
import com.mihigo.main.models.Sectors;
import com.mihigo.main.models.Site;
import com.mihigo.main.models.SiteStatus;
import com.mihigo.main.models.UserRole;
import com.mihigo.main.models.Users;
import com.mihigo.main.payloads.SitePayload;
import com.mihigo.main.payloads.UserInputPayload;
import com.mihigo.main.payloads.UserOutputPayload;
import com.mihigo.main.payloads.UserPayloadRef;
import com.mihigo.main.repositories.ProvinceRepo;
import com.mihigo.main.service.districts.DistrictsServices;
import com.mihigo.main.service.sectors.SectorServices;
import com.mihigo.main.service.site.SiteInterface;
import com.mihigo.main.service.users.UserServices;

@RestController
@RequestMapping(value = "/kigali/")
public class KrgController {

	@Autowired
	private UserServices uzer;

	@Autowired
	private SiteInterface siteserv;

	@Autowired
	private ProvinceRepo prorepo;
	@Autowired
	private DistrictsServices ds;
	@Autowired
	private SectorServices ss;

	@RequestMapping(method = RequestMethod.POST, path = "newAdmin")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createUser(@RequestBody UserInputPayload user) throws InvalidParameters {
		try {
			Users uz = uzer.createAdminUser(user.getEmail(), user.getPhone(), user.getProvince(), user.getDistrict(),
					user.getSector(), user.getNames(), user.getGender(), user.getRole(), user.getUsername(),
					user.getPassword());
			UserOutputPayload outz = new UserOutputPayload(uz.getId(), uz.getEmail(), uz.getPhone(), uz.getProvince(),
					uz.getDistrict(), uz.getSector(), uz.getNames(), uz.getGender(), uz.getRole(), uz.getUsername(),
					uz.getRefKey(), uz.getRegDate());

			return new ResponseEntity<UserOutputPayload>(outz, HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "siteAdmin")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createSiteUser(@RequestBody UserInputPayload user) throws InvalidParameters {
		try {
			Users uz = uzer.createSiteUser(user.getEmail(), user.getPhone(), user.getProvince(), user.getDistrict(),
					user.getSector(), user.getNames(), user.getGender(), user.getRole(), user.getSite(),
					user.getUsername(), user.getPassword());
			UserOutputPayload outz = new UserOutputPayload(uz.getId(), uz.getEmail(), uz.getPhone(), uz.getProvince(),
					uz.getDistrict(), uz.getSector(), uz.getNames(), uz.getGender(), uz.getRole(), uz.getUsername(),
					uz.getRefKey(), uz.getRegDate());

			return new ResponseEntity<UserOutputPayload>(outz, HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "siteAdminByRef")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createSiteUserByRef(@RequestBody UserPayloadRef user) throws InvalidParameters {
		try {
			Users uz = uzer.createUserBySiteRefKey(user.getEmail(), user.getPhone(), user.getProvince(),
					user.getDistrict(), user.getSector(), user.getNames(), user.getGender(), user.getRole(),
					user.getSiteRefKey(), user.getUsername(), user.getPassword());

			UserOutputPayload outz = new UserOutputPayload(uz.getId(), uz.getEmail(), uz.getPhone(), uz.getProvince(),
					uz.getDistrict(), uz.getSector(), uz.getNames(), uz.getGender(), uz.getRole(), uz.getUsername(),
					uz.getRefKey(), uz.getRegDate());

			return new ResponseEntity<UserOutputPayload>(outz, HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "newSite")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createSite(@RequestBody SitePayload si) throws InvalidParameters {
		try {
			Site s = siteserv.createSite(si.getEmail(), si.getPhone(), si.getProvince(), si.getDistrict(),
					si.getSector(), si.getName(), si.getStatus(), si.getPrice());
			return new ResponseEntity<String>(s.getRefKey(), HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/allSite")
	@CrossOrigin(origins = "*")
	public List<Site> allSite() throws InvalidParameters {
		try {
			return siteserv.allSite();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/all_provinces")
	@CrossOrigin(origins = "*")
	public List<?> getAllProvinces() throws InvalidParameters {
		try {
			return prorepo.findAll();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/getDistrict")
	@CrossOrigin(origins = "*")
	public List<?> getDistrictByProvinces(@RequestParam(value = "id") int id) throws InvalidParameters {
		try {
			List<Districts> l = ds.getDistrictsById(id);
			return l;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/getSectors")
	@CrossOrigin(origins = "*")
	public List<?> getSectorsByDistrict(@RequestParam(value = "id") int id) throws InvalidParameters {
		try {
			List<Sectors> l = ss.getSectorsByDistrictsId(id);
			return l;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/getUserRole")
	@CrossOrigin(origins = "*")
	public List<String> userroles() throws InvalidParameters {
		try {
			List<String> l = new ArrayList<>();
			for (Enum<UserRole> x : UserRole.values()) {
				l.add(x.toString());
			}
			return l;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/getGender")
	@CrossOrigin(origins = "*")
	public List<String> usergender() throws InvalidParameters {
		try {
			List<String> l = new ArrayList<>();
			for (Enum<Gender> x : Gender.values()) {
				l.add(x.toString());
			}
			return l;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/allSiteStatus")
	@CrossOrigin(origins = "*")
	public List<String> siteStatus() throws InvalidParameters {
		try {
			List<String> l = new ArrayList<>();
			for (Enum<SiteStatus> x : SiteStatus.values()) {
				l.add(x.toString());
			}
			return l;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/getSiteByRef")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getSiteByrefKey(@RequestParam(value = "ref") String ref) throws InvalidParameters {
		try {
			Site sit = siteserv.searchByReferenceKey(ref);
			return new ResponseEntity<SitePayload>(new SitePayload(sit.getEmail(), sit.getPhone(), sit.getProvince(),
					sit.getDistrict(), sit.getSector(), sit.getName(), sit.getStatus().toString(), sit.getPrice(),
					sit.getRefKey()), HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}
}
