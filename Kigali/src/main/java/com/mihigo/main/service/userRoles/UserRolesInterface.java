package com.mihigo.main.service.userRoles;

import java.util.List;

import com.mihigo.main.models.Role;

public interface UserRolesInterface {

	Role createNewRole(String role);
	
	Role getRole(String name);

	List<Role> getAllUserRoles();

}
