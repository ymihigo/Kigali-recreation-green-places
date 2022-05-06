package com.mihigo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {

	@Query("From Site where refKey = :ref")
	Site searchByrefKey(@Param(value = "ref") String ref);
	

}
