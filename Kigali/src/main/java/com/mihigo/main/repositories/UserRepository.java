package com.mihigo.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	@Query("FROM Users u where u.username= :username")
	Users searchUserByUsername(@Param("username") String username);

	@Query("FROM Users u where u.email = :email")
	Users searchByemail(@Param("email") String email);

	@Query("FROM Users u where u.refKey = :refkey")
	Users searchByrefKey(@Param("refkey") String refkey);

	@Query("From Users u where u.phone = :phone")
	Users searchByPhone(@Param("phone") String phone);
	
	@Query("FROM Users u JOIN Site s ON u.site = s.id WHERE s.refKey = :ref")
	List<Users> allUserBySiteRefKey(@Param(value = "ref") String siteRefKey);

}
