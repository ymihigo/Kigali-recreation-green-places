package com.mihigo.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mihigo.main.service.site.SiteInterface;
import com.mihigo.main.service.userRoles.UserRolesInterface;
import com.mihigo.main.service.users.UserServices;

@SpringBootApplication
public class KigaliApplication {

	public static void main(String[] args) {
		SpringApplication.run(KigaliApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserServices userService, UserRolesInterface roles,SiteInterface site) {
		return args -> {
			roles.createNewRole("Admin");
			roles.createNewRole("Site_user");
			roles.createNewRole("Site_Constructor");
//			site.createSite("site@gmail.com", "+250786278334", "Kigali", "Kicukiro", "Gahanga", "site", "Working", 0);
//			userService.createUserBySiteRefKey("siteadmin@gmail.com", "+250786278354","Kigali", "Kicukiro", "Gahanga", "site Admin", "Male", "Site_user", "QvJkomx97GhfEsOc", "siteadmin", "Password@123");
//			userService.createAdminUser("siteuser@gmail.com", "+250786278334", "Kigali", "Kicukiro", "Gahanga", "Site_user",
//					Gender.Male.toString(), "siteuser", "Password@123");
//			userService.createAdminUser("siteconstructor@gmail.com", "+250786278335", "Kigali", "Kicukiro", "Gahanga", "Site_constructor",
//					Gender.Male.toString(), "SiteConstructor", "Password@123");
//			userService.createRole(new Role("ROLE_ADMIN"));
//			userService.createRole(new Role("ROLE_MANAGER"));
//			userService.createRole(new Role("ROLE_USER"));
//			userService.createRole(new Role("ROLE_SUPER_ADMIN"));
//
//			userService.createUser(new Uzer("admin", "admin", "password"));
//			userService.createUser(new Uzer("manager", "manager", "password"));
//			userService.createUser(new Uzer("user", "user", "password"));
//			userService.createUser(new Uzer("super", "super", "password"));
//
//			userService.addRoleToUser("admin", "ROLE_ADMIN");
//			userService.addRoleToUser("admin", "ROLE_USER");
//			userService.addRoleToUser("manager", "ROLE_MANAGER");
//			userService.addRoleToUser("super", "ROLE_SUPER_ADMIN");
		};
	}
}
