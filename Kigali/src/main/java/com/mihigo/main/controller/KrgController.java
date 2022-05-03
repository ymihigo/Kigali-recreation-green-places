package com.mihigo.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mihigo.main.exceptionHandler.InvalidParameters;
import com.mihigo.main.models.Site;
import com.mihigo.main.models.Users;
import com.mihigo.main.payloads.SitePayload;
import com.mihigo.main.payloads.UserInputPayload;
import com.mihigo.main.payloads.UserOutputPayload;
import com.mihigo.main.service.site.SiteInterface;
import com.mihigo.main.service.users.UserServices;

@RestController
@RequestMapping(value = "/kigali/")
public class KrgController {

	@Autowired
	private UserServices uzer;

	@Autowired
	private SiteInterface siteserv;

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

	@RequestMapping(method = RequestMethod.POST, path = "newSite")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> createSite(@RequestBody SitePayload si) throws InvalidParameters {
		try {
			Site s = siteserv.createSite(si.getEmail(), si.getPhone(), si.getProvince(), si.getProvince(),
					si.getSector(), si.getName(), si.getStatus(), si.getPrice());
			return new ResponseEntity<Site>(s, HttpStatus.OK);
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}

	public List<Site> allSite() throws InvalidParameters {
		try {
			return siteserv.allSite();
		} catch (Exception ex) {
			throw new InvalidParameters(ex.getMessage());
		}
	}
}
