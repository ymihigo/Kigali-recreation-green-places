package com.mihigo.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.VisitSite;

@Repository
public interface VisitRepository extends JpaRepository<VisitSite, Long> {

	List<VisitSite> findAllByOrderByIdDesc();
	
	List<VisitSite> findAllBySite(Site site);
}
