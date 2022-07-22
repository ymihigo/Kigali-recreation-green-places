/**
 * 
 */
package com.mihigo.main.service.userRoles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mihigo.main.models.Role;
import com.mihigo.main.repositories.UserRolesRepository;

@Service
public class UserRolesInterfaceImpl implements UserRolesInterface {

	@Autowired
	private UserRolesRepository userRoleRepo;

	@Override
	public Role createNewRole(String role) {
		try {
			if (role.isBlank()) {
				throw new RuntimeException("invalid role");
			}
			String rolee = role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();
			Role rol = getRole(rolee);

			if (rol != null) {
				return rol;
			}
			return userRoleRepo.saveAndFlush(new Role(rolee));
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Role> getAllUserRoles() {
		try {
			return userRoleRepo.findAll();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Role getRole(String name) {
		try {
			if (name.isBlank()) {
				throw new RuntimeException("role is required");
			}
			return userRoleRepo.findByName(name);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
