package com.mihigo.main.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.exceptionHandler.InvalidParameters;
import com.mihigo.main.models.BookingSite;
import com.mihigo.main.models.Districts;
import com.mihigo.main.models.Gender;
import com.mihigo.main.models.ReportTyoe;
import com.mihigo.main.models.Sectors;
import com.mihigo.main.models.Site;
import com.mihigo.main.models.SiteStatus;
import com.mihigo.main.models.Role;
import com.mihigo.main.models.Users;
import com.mihigo.main.payloads.BookingPayload;
import com.mihigo.main.payloads.SitePayload;
import com.mihigo.main.payloads.UserInputPayload;
import com.mihigo.main.payloads.UserOutputPayload;
import com.mihigo.main.payloads.UserPayloadRef;
import com.mihigo.main.payloads.VisitTable;
import com.mihigo.main.repositories.ProvinceRepo;
import com.mihigo.main.service.booking.BookingService;
import com.mihigo.main.service.districts.DistrictsServices;
import com.mihigo.main.service.reports.ReportServiceInterface;
import com.mihigo.main.service.sectors.SectorServices;
import com.mihigo.main.service.site.SiteInterface;
import com.mihigo.main.service.userRoles.UserRolesInterface;
import com.mihigo.main.service.users.UserServices;
import com.mihigo.main.service.visitsite.VisitInterface;

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

	@Autowired
	private VisitInterface visitService;

	@Autowired
	private ReportServiceInterface report_service;

	@Autowired
	private UserRolesInterface role_Service;

	@Autowired
	private BookingService bookserv;

	@RequestMapping(method = RequestMethod.POST, path = "admin/newAdmin")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createUser(@RequestBody UserInputPayload user) throws InvalidParameters {
		try {
			Users uz = uzer.createAdminUser(user.getEmail(), user.getPhone(), user.getProvince(), user.getDistrict(),
					user.getSector(), user.getNames(), user.getGender(), user.getUsername(), user.getPassword());
			UserOutputPayload outz = new UserOutputPayload(uz.getEmail(), uz.getPhone(), uz.getProvince(),
					uz.getDistrict(), uz.getSector(), uz.getNames(), uz.getGender(), uz.getRole(), uz.getUsername(),
					uz.getRefKey(), uz.getRegDate(), uz.getStatus());

			return new ResponseEntity<UserOutputPayload>(outz, HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "user/getUser")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getUserByUsername(@RequestParam("username") String username) throws InvalidParameters {
		try {
			Users uz = uzer.getUser(username);
			Role rol = uz.getRole().stream().findFirst().get();

			String siteRefKey = "";

			if (rol.getName().equalsIgnoreCase("Admin")) {
				siteRefKey = "";
			} else {
				siteRefKey = uz.getSite().getRefKey();
			}
			return ResponseEntity.ok()
					.body(new UserOutputPayload(uz.getEmail(), uz.getPhone(), uz.getProvince(), uz.getDistrict(),
							uz.getSector(), uz.getNames(), uz.getGender(), uz.getRole(), uz.getUsername(),
							uz.getRefKey(), uz.getRegDate(), uz.getStatus(), siteRefKey));
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "admin/alluzers")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getUsers() throws InvalidParameters {
		try {
//
//			URI uri = URI.create(
//					ServletUriComponentsBuilder.fromCurrentContextPath().path("kigali/admin/alluzers").toUriString());
//			return ResponseEntity.ok().body(uzer.createAdminUser(user.getEmail(), user.getPhone(), user.getProvince(), user.getDistrict(), user.getSector(), user.getNames(), user.getGender(), user.getUsername(), user.getPassword()));
			return ResponseEntity.ok().body(uzer.getAllUsers());
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "user/siteAdminByRef")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createSiteUserByRef(@RequestBody UserPayloadRef user) throws InvalidParameters {
		try {
			Users uz = uzer.createUserBySiteRefKey(user.getEmail(), user.getPhone(), user.getProvince(),
					user.getDistrict(), user.getSector(), user.getNames(), user.getGender(), user.getRole(),
					user.getSiteRefKey(), user.getUsername(), user.getPassword());

			UserOutputPayload outz = new UserOutputPayload(uz.getEmail(), uz.getPhone(), uz.getProvince(),
					uz.getDistrict(), uz.getSector(), uz.getNames(), uz.getGender(), uz.getRole(), uz.getUsername(),
					uz.getRefKey(), uz.getRegDate(), uz.getStatus());

			return new ResponseEntity<UserOutputPayload>(outz, HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "admin/newSite")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createSite(@RequestBody SitePayload si) throws InvalidParameters {
		try {
			Site s = siteserv.createSite(si.getEmail(), si.getPhone(), si.getProvince(), si.getDistrict(),
					si.getSector(), si.getName(), si.getStatus(), si.getPrice(), si.getAbout(), si.getLongitude(),
					si.getLatitude(), si.isBookable());
			return new ResponseEntity<String>(s.getRefKey(), HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("admin/allSite")
	@CrossOrigin(origins = "*")
	public List<Site> allSite() throws InvalidParameters {
		try {
			return siteserv.allSite();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/all/all_provinces")
	@CrossOrigin(origins = "*")
	public List<?> getAllProvinces() throws InvalidParameters {
		try {
			return prorepo.findAll();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/all/getDistrict")
	@CrossOrigin(origins = "*")
	public List<?> getDistrictByProvinces(@RequestParam(value = "id") int id) throws InvalidParameters {
		try {
			List<Districts> l = ds.getDistrictsById(id);
			return l;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/all/getSectors")
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
	public List<Role> userroles() throws InvalidParameters {
		try {
			return role_Service.getAllUserRoles();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/all/getGender")
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

	@GetMapping("allSiteStatus")
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

	@GetMapping("user/getSiteByRef")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getSiteByrefKey(@RequestParam(value = "ref") String ref) throws InvalidParameters {
		try {
			Site sit = siteserv.searchByReferenceKey(ref);
			return new ResponseEntity<SitePayload>(
					new SitePayload(sit.getEmail(), sit.getPhone(), sit.getProvince(), sit.getDistrict(),
							sit.getSector(), sit.getName(), sit.getStatus().toString(), sit.getPrice(), sit.getRefKey(),
							sit.getAbout(), sit.getLongitude(), sit.getLatitude(), sit.getPhotos(), sit.isBookable()),
					HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("all/getSiteByRef")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> allSiteByrefKey(@RequestParam(value = "ref") String ref) throws InvalidParameters {
		try {
			Site sit = siteserv.searchByReferenceKey(ref);
			return new ResponseEntity<SitePayload>(
					new SitePayload(sit.getEmail(), sit.getPhone(), sit.getProvince(), sit.getDistrict(),
							sit.getSector(), sit.getName(), sit.getStatus().toString(), sit.getPrice(), sit.getRefKey(),
							sit.getAbout(), sit.getLongitude(), sit.getLatitude(), sit.getPhotos(), sit.isBookable()),
					HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/allSiteUserByRef")
	@CrossOrigin(origins = "*")
	public List<Users> getUsersBySiteRefKey(@RequestParam(value = "ref") String ref) throws InvalidParameters {
		try {
			List<Users> lu = siteserv.getUsersBySiteRefKey(ref);
			return lu;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("admin/allUsers")
	@CrossOrigin(origins = "*")
	public List<UserOutputPayload> getAlltUsers() throws InvalidParameters {
		try {
			List<Users> lu = uzer.getAllUsers();
			List<UserOutputPayload> lo = new ArrayList<>();
			lu.forEach(x -> {
				lo.add(new UserOutputPayload(x.getEmail(), x.getPhone(), x.getProvince(), x.getDistrict(),
						x.getDistrict(), x.getNames(), x.getGender(), x.getRole(), x.getUsername(), x.getRefKey(),
						x.getRegDate(), x.getStatus()));
			});
			return lo;
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("user/userbyRef")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Users> getUserByKey(@RequestParam("ref") String refKey) throws InvalidParameters {
		try {
			Users u = uzer.getUserByRefKey(refKey);
			return new ResponseEntity<Users>(u, HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@PostMapping("/camera_visit/{key}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> cameraVisitors(@PathVariable String key) throws InvalidParameters {
		try {
			visitService.visitByCamera(key);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("admin/site_count")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> countByStatus(@RequestParam("status") SiteStatus status) throws InvalidParameters {
		try {
			switch (status) {
			case Working:
				return ResponseEntity.ok().body(siteserv.countByStatus(SiteStatus.Working));
			case Not_Working:
				return ResponseEntity.ok().body(siteserv.countByStatus(SiteStatus.Not_Working));
			case Under_Construction:
				return ResponseEntity.ok().body(siteserv.countByStatus(SiteStatus.Under_Construction));
			default:
				throw new InvalidParameters("Invalid Status");
			}
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/admin/all_site_count")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> countSite() throws InvalidParameters {
		try {
			return ResponseEntity.ok().body(siteserv.countAllSites());
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("admin/all_sites_by_status")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> listSiteByStatus(@RequestParam("status") SiteStatus status) throws InvalidParameters {
		try {
			switch (status) {
			case Working:
				return ResponseEntity.ok().body(siteserv.findByStatus(SiteStatus.Working));
			case Not_Working:
				return ResponseEntity.ok().body(siteserv.findByStatus(SiteStatus.Not_Working));
			case Under_Construction:
				return ResponseEntity.ok().body(siteserv.findByStatus(SiteStatus.Under_Construction));
			default:
				throw new InvalidParameters("Invalid status");
			}
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/all/all_sites_by_status")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> loadAllWorkingSite(@RequestParam("status") SiteStatus status) throws InvalidParameters {
		try {
			switch (status) {
			case Working:
				return ResponseEntity.ok().body(siteserv.findByStatus(SiteStatus.Working));
			case Not_Working:
				return ResponseEntity.ok().body(siteserv.findByStatus(SiteStatus.Not_Working));
			case Under_Construction:
				return ResponseEntity.ok().body(siteserv.findByStatus(SiteStatus.Under_Construction));
			default:
				throw new InvalidParameters("Invalid status");
			}
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("search_province")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> findByProvinceName(@RequestParam("province") String province) throws InvalidParameters {
		try {
			return ResponseEntity.ok().body(prorepo.findByProvince(province));
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("search_district")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> findByDistrictName(@RequestParam("district") String district) throws InvalidParameters {
		try {
			return ResponseEntity.ok().body(ds.findByDistrictsName(district));
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@PostMapping("user/upload_profile")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> uploadProfilePicture(@RequestParam("key") String key,
			@RequestParam("image") MultipartFile image) {
		try {
			uzer.updateProfilePicture(key, image);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@PostMapping("user/report")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createReport(@RequestParam("reportTitle") String reportTitle,
			@RequestParam(value = "userRef", required = true) String userRef,
			@RequestParam(value = "report", required = true) String report,
			@RequestParam(value = "document", required = false) MultipartFile document) {
		try {
			if (document == null) {
				report_service.createReport(reportTitle, userRef, report);
				return ResponseEntity.ok().build();
			} else {
				report_service.createReport(reportTitle, userRef, report, document);
				return ResponseEntity.ok().build();
			}
		} catch (Exception ex) {
			throw new InvalidParameterException(ex.getMessage());
		}
	}

	@PostMapping("user/visit")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> visitBySite(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "gender", required = true) Gender gender,
			@RequestParam(value = "province", required = false) String province,
			@RequestParam(value = "district", required = false) String district,
			@RequestParam(value = "sector", required = false) String sector,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "SiteRefKey", required = true) String siteRef) throws InvalidParameters {
		try {
			visitService.visitors(email, phone, province, district, sector, name, gender, siteRef);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/all_visitors")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> findAllVisitors() throws InvalidParameters {
		try {
			List<VisitTable> li = visitService.findAllVisitors();
			return ResponseEntity.ok().body(li);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("/all_visitors/{field}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> findAllVisitorsSortByfield(@PathVariable String field) throws InvalidParameters {
		try {
			List<VisitTable> li = visitService.findAllbyField(field);
			return ResponseEntity.ok().body(li);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping(path = "user/allVisitors")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> allVisitorsBySite(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("siteRefKey") String siteRefKey) {
		try {
			List<VisitTable> li = visitService.visitorsByPeriodAndSite(from, to, siteRefKey);
			return ResponseEntity.ok().body(li);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@GetMapping("/pagination/{offset}/{pagesize}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> findAllVisitorsSortByfield(@PathVariable("offset") int offset,
			@PathVariable("pagesize") int pagesize) throws InvalidParameters {
		try {
			List<VisitTable> li = visitService.findAllWithPagination(offset, pagesize);
			return ResponseEntity.ok().body(li);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping(value = { "user/countvisitors/{period}", "user/countvisitors/{period}/{ref}" })
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> countVisitors(@PathVariable(value = "ref", required = false) String ref,
			@PathVariable(value = "period", required = true) String period) throws InvalidParameters {
		try {

			if (ref == null) {
				return ResponseEntity.ok().body(visitService.countAll(period));
			} else {
				return ResponseEntity.ok().body(visitService.countAll(period, ref));
			}
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST, path = "user/uploadSiteImages")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> checkFileType(@RequestParam("refKey") String siteRefKey,
			@RequestParam("imageOne") List<MultipartFile> photo1) throws InvalidParameters {
		try {
			return ResponseEntity.ok().body(siteserv.addSiteImages(siteRefKey, photo1));
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "user/getReportStatus")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> reportStatus() throws InvalidParameters {
		try {
			List<String> reportList = new ArrayList<>();
			for (Enum<ReportTyoe> x : ReportTyoe.values()) {
				reportList.add(x.toString());
			}
			return ResponseEntity.ok().body(reportList);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping(value = { "admin/allreports/{from}/{to}", "user/{from}/{to}/{refKey}" })
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getReport(@PathVariable(value = "from", required = true) String from,
			@PathVariable(value = "to", required = true) String to,
			@PathVariable(value = "refKey", required = false) String refKey) throws InvalidParameters {
		try {
			if (refKey == null) {
				return ResponseEntity.ok().body(report_service.getAllReport(from, to));
			} else {
				return ResponseEntity.ok().body(report_service.getAllReport(from, to, refKey));
			}
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping(path = "admin/topvisiting")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> topVisiting(@RequestParam("period") String period) throws InvalidParameters {
		try {
			return ResponseEntity.ok().body(visitService.topselling(period));
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@PostMapping("all/book")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createBooking(@RequestBody BookingPayload bookingPayload) throws InvalidParameters {
		try {
			return ResponseEntity.ok(bookserv.createBooking(bookingPayload.getNamez(), bookingPayload.getRefKey(),
					bookingPayload.getPhone(), bookingPayload.getHowMany(), bookingPayload.getVisitDateTime(),
					bookingPayload.getEmail()));
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@GetMapping("user/getbookings")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> allSiteBooking(@RequestParam("siteRefKey") String refKey) throws InvalidParameters {
		try {
			return ResponseEntity.ok().body(bookserv.findBookingBySite(refKey));
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	@PostMapping("user/changebookingstatus")
	public ResponseEntity<?> changeBookingStatus(@RequestParam("refKey") String refKey) {
		try {
			return ResponseEntity.ok().body(bookserv.changeStatus(refKey));
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@GetMapping("user/revenuesummation")
	public ResponseEntity<?> siteRevenue(@RequestParam("period") String period, @RequestParam("refKey") String refKey) {
		try {
			return ResponseEntity.ok().body(visitService.countRevenue(period, refKey));
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	@GetMapping("user/sitecustomers")
	public ResponseEntity<?> siteCustomers(@RequestParam("period") String period, @RequestParam("refKey") String refKey) {
		try {
			return ResponseEntity.ok().body(visitService.countAll(period, refKey));
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
}