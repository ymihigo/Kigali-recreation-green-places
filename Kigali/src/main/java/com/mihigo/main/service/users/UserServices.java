package com.mihigo.main.service.users;


import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Users;

public interface UserServices {
	Users createAdminUser(String email, String phone, String province, String district, String sector, String names,
			String gender, String role, String username, String password);

	Users createSiteUser(String email, String phone, String province, String district, String sector, String names,
			String gender, String role, int site, String username, String password);

	Users updateAdminUser(String email, String phone, String province, String district, String sector, String names,
			String gender, int id, String role, String status, String username, String refKey);

	Users updateSiteUser(String email, String phone, String province, String district, String sector, String names,
			String gender, int id, String role, String status, String username, String refKey);

	Users updateProfilePicture(int userid, MultipartFile image);

	Users changeuserPassword(String refkey, String oldpassword, String newPassword);

	Users searchUserByrefKey(String refKey);
	
	Users createUserBySiteRefKey(String email, String phone, String province, String district, String sector, String names,
			String gender, String role, String refSite, String username, String password);
}
