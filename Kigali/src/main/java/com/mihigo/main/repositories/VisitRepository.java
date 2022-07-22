package com.mihigo.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.VisitSite;

@Repository
public interface VisitRepository extends JpaRepository<VisitSite, Long> {

	List<VisitSite> findAllByOrderByIdDesc();

	List<VisitSite> findAllBySite(Site site);

	long countAllBySite(Site site);
	
	@Query("SELECT COUNT(v.id) FROM VisitSite v WHERE DATE(v.date) =DATE(NOW()) AND site = :site")
	long countvisitorsToday(@Param("site") Site site);

	@Query("SELECT COUNT(v.id) FROM VisitSite v WHERE DATE(v.date) =DATE(NOW())")
	long countvisitorsToday();

	@Query("SELECT COUNT(v.id) FROM VisitSite v WHERE MONTH(v.date) =MONTH(NOW()) AND site = :site")
	long countvisitorsMonth(@Param("site") Site site);

	@Query("SELECT COUNT(v.id) FROM VisitSite v WHERE MONTH(v.date) =MONTH(NOW())")
	long countvisitorsMonth();

	@Query("SELECT COUNT(v.id) FROM VisitSite v WHERE YEAR(v.date) =YEAR(NOW()) AND site = :site")
	long countvisitorsYear(@Param("site") Site site);

	@Query("SELECT COUNT(v.id) FROM VisitSite v WHERE YEAR(v.date) =YEAR(NOW())")
	long countvisitorsYear();

}
