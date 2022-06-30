package com.mihigo.main.service.users;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.exceptionHandler.InvalidParameters;
import com.mihigo.main.models.Gender;
import com.mihigo.main.models.Site;
import com.mihigo.main.models.UserRole;
import com.mihigo.main.models.Users;
import com.mihigo.main.repositories.SiteRepository;
import com.mihigo.main.repositories.UserRepository;
import com.mihigo.main.tools.PasswordEncoder;
import com.mihigo.main.tools.Randomazation;

@Service
public class UserServiceImplementation implements UserServices {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private PasswordEncoder hashPass;

	@Autowired
	private Randomazation ran;

	@Autowired
	private SiteRepository siterepo;

	@Override
	public Users createAdminUser(String email, String phone, String province, String district, String sector,
			String names, String gender, String role, String username, String password) {
		try {

			if (email.isEmpty() || phone.isEmpty() || province.isEmpty() || district.isEmpty() || sector.isEmpty()
					|| names.isEmpty() || gender.isEmpty() || role.isEmpty() || username.isEmpty()
					|| password.isEmpty()) {
				throw new RuntimeException("Please fill all requirements");
			}
			UserRole rol = UserRole.valueOf(role);
			Gender gend = Gender.valueOf(gender);
			if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
				throw new RuntimeException("Invalid email format");
			}
			if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,16}$")) {
				throw new RuntimeException("Weak password !!");
			}

			Users uz = userrepo.searchUserByUsername(username);

			if (uz != null) {
				throw new RuntimeException("Username alread exist !!");
			}

			Users uemail = userrepo.searchByemail(email);

			if (uemail != null) {
				throw new RuntimeException("email alread exist !!");
			}

			Users up = userrepo.searchByPhone(phone);

			if (up != null) {
				throw new RuntimeException("Phone number already exist");
			}
			if (!phone.matches("^\\+2507[2-3-8-9]{1}\\d{7}$")) {
				throw new RuntimeException("Invalid phone number format");
			}

			String hashedPassword = hashPass.hashPassword(password);

			String refkey = ran.random(21);

			Users u = userrepo.saveAndFlush(new Users(email, phone, province, district, sector, names, gend, rol,
					username, hashedPassword, refkey));
			return u;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

	}

	@Override
	public Users createSiteUser(String email, String phone, String province, String district, String sector,
			String names, String gender, String role, int site, String username, String password) {

		try {

			if (email.isEmpty() || phone.isEmpty() || province.isEmpty() || district.isEmpty() || sector.isEmpty()
					|| names.isEmpty() || gender.isEmpty() || role.isEmpty() || username.isEmpty()
					|| password.isEmpty()) {
				throw new RuntimeException("Please fill all requirements");
			}

			Optional<Site> si = siterepo.findById(site);

			if (si.isEmpty()) {
				throw new InvalidParameters("Invalid site");
			}

			UserRole rol = UserRole.valueOf(role);
			Gender gend = Gender.valueOf(gender);
			if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
				throw new RuntimeException("Invalid email format");
			}
			if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,16}$")) {
				throw new RuntimeException("Weak password !!");
			}

			Users uz = userrepo.searchUserByUsername(username);

			if (uz != null) {
				throw new RuntimeException("Username alread exist !!");
			}

			Users uemail = userrepo.searchByemail(email);

			if (uemail != null) {
				throw new RuntimeException("email alread exist !!");
			}

			Users up = userrepo.searchByPhone(phone);

			if (up != null) {
				throw new RuntimeException("Phone number already exist");
			}
			if (!phone.matches("^\\+2507[2-3-8-9]{1}\\d{7}$")) {
				throw new RuntimeException("Invalid phone number format");
			}

			String hashedPassword = hashPass.hashPassword(password);

			String refkey = ran.random(21);

			Users u = userrepo.saveAndFlush(new Users(email, phone, province, district, sector, names, gend, rol,
					si.get(), username, hashedPassword, refkey));
			return u;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Users updateAdminUser(String email, String phone, String province, String district, String sector,
			String names, String gender, int id, String role, String status, String username, String refKey) {
		try {
			Optional<Users> us = userrepo.findById(id);
			if (us.isEmpty()) {
				throw new RuntimeException("User doesn't exist");
			}

			if (email.isEmpty() || phone.isEmpty() || province.isEmpty() || district.isEmpty() || sector.isEmpty()
					|| names.isEmpty() || gender.isEmpty() || role.isEmpty() || username.isEmpty()) {
				throw new RuntimeException("Please fill all requirements");
			}
			Gender gend = Gender.valueOf(gender);
			if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
				throw new RuntimeException("Invalid email format");
			}

			Users uz = userrepo.searchUserByUsername(username);

			if (uz != null) {
				throw new RuntimeException("Username alread exist !!");
			}

			Users uemail = userrepo.searchByemail(email);

			if (uemail != null) {
				throw new RuntimeException("email alread exist !!");
			}

			Users up = userrepo.searchByPhone(phone);

			if (up != null) {
				throw new RuntimeException("Phone number already exist");
			}
			if (!phone.matches("^\\+2507[2-3-8-9]{1}\\d{7}$")) {
				throw new RuntimeException("Invalid phone number format");
			}

			String refkey = ran.random(21);

			Users u = us.get();

			u.setDistrict(district);
			u.setEmail(email);
			u.setGender(gend);
			u.setNames(names);
			u.setPhone(phone);
			u.setProvince(province);
			u.setRefKey(refkey);
			u.setSector(sector);
			u.setUsername(username);
			userrepo.saveAndFlush(u);
			return u;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

	}

	@Override
	public Users updateSiteUser(String email, String phone, String province, String district, String sector,
			String names, String gender, int id, String role, String status, String username, String refKey) {
		try {
			Optional<Users> us = userrepo.findById(id);
			if (us.isEmpty()) {
				throw new RuntimeException("User doesn't exist");
			}

			if (email.isEmpty() || phone.isEmpty() || province.isEmpty() || district.isEmpty() || sector.isEmpty()
					|| names.isEmpty() || gender.isEmpty() || role.isEmpty() || username.isEmpty()) {
				throw new RuntimeException("Please fill all requirements");
			}

			Gender gend = Gender.valueOf(gender);
			if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
				throw new RuntimeException("Invalid email format");
			}

			Users uz = userrepo.searchUserByUsername(username);

			if (uz != null) {
				throw new RuntimeException("Username alread exist !!");
			}

			Users uemail = userrepo.searchByemail(email);

			if (uemail != null) {
				throw new RuntimeException("email alread exist !!");
			}

			Users up = userrepo.searchByPhone(phone);

			if (up != null) {
				throw new RuntimeException("Phone number already exist");
			}
			if (!phone.matches("^\\+2507[2-3-8-9]{1}\\d{7}$")) {
				throw new RuntimeException("Invalid phone number format");
			}

			String refkey = ran.random(21);

			Users u = us.get();

			u.setDistrict(district);
			u.setEmail(email);
			u.setGender(gend);
			u.setNames(names);
			u.setPhone(phone);
			u.setProvince(province);
			u.setRefKey(refkey);
			u.setSector(sector);
			u.setUsername(username);
			userrepo.saveAndFlush(u);
			return u;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Users updateProfilePicture(String userKey, MultipartFile image) {
		try {
			String filename = StringUtils.cleanPath(image.getOriginalFilename());
			if (filename.isBlank() || filename.contains("..")) {
				throw new RuntimeException("Invalid file");
			}

			Users us = userrepo.searchByrefKey(userKey);

			if (us == null) {
				throw new RuntimeException("Invalid user");
			}

			String file = Base64.getEncoder().encodeToString(image.getBytes());

			us.setImage(file);
			return userrepo.saveAndFlush(us);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Users changeuserPassword(String refkey, String oldpassword, String newPassword) {
		try {

			Users us = userrepo.searchByrefKey(refkey);

			if (us == null) {
				throw new RuntimeException("Invalid user");
			}
			if (us.getPassword() != hashPass.hashPassword(oldpassword)) {
				throw new RuntimeException("Invalid old password");
			}
			if (!newPassword.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,16}$")) {
				throw new RuntimeException("Weak password !!");
			}

			us.setPassword(hashPass.hashPassword(newPassword));
			return userrepo.saveAndFlush(us);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Users searchUserByrefKey(String refKey) {
		try {
			if (refKey.isEmpty()) {
				throw new RuntimeException("Empty reference key");
			}

			Users uz = userrepo.searchByrefKey(refKey);
			if (uz == null) {
				throw new RuntimeException();
			}
			return uz;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Users createUserBySiteRefKey(String email, String phone, String province, String district, String sector,
			String names, String gender, String role, String refSite, String username, String password) {
		try {

			if (email.isEmpty() || phone.isEmpty() || province.isEmpty() || district.isEmpty() || sector.isEmpty()
					|| names.isEmpty() || gender.isEmpty() || role.isEmpty() || username.isEmpty() || password.isEmpty()
					|| refSite.isEmpty()) {
				throw new RuntimeException("Please fill all requirements");
			}

			Site si = siterepo.searchByrefKey(refSite);

			if (si == null) {
				throw new InvalidParameters("Invalid site");
			}

			UserRole rol = UserRole.valueOf(role);
			Gender gend = Gender.valueOf(gender);
			if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
				throw new RuntimeException("Invalid email format");
			}
			if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,16}$")) {
				throw new RuntimeException("Weak password !!");
			}

			Users uz = userrepo.searchUserByUsername(username);

			if (uz != null) {
				throw new RuntimeException("Username alread exist !!");
			}

			Users uemail = userrepo.searchByemail(email);

			if (uemail != null) {
				throw new RuntimeException("email alread exist !!");
			}

			Users up = userrepo.searchByPhone(phone);

			if (up != null) {
				throw new RuntimeException("Phone number already exist");
			}
			if (!phone.matches("^\\+2507[2-3-8-9]{1}\\d{7}$")) {
				throw new RuntimeException("Invalid phone number format");
			}

			String hashedPassword = hashPass.hashPassword(password);

			String refkey = ran.random(21);

			Users u = userrepo.saveAndFlush(new Users(email, phone, province, district, sector, names, gend, rol, si,
					username, hashedPassword, refkey));
			return u;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Users> getAllUsers() {
		try {
			return userrepo.findAll();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Users getUserByRefKey(String refKey) {
		try {
			if (refKey.isEmpty()) {
				throw new RuntimeException("Invalid Key");
			}
			Users ux = userrepo.searchByrefKey(refKey);
			if (ux == null) {
				throw new RuntimeException("Invalid Key");
			}
			return ux;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
