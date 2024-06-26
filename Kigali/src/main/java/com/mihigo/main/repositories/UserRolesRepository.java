package com.mihigo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Role;

@Repository
public interface UserRolesRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
