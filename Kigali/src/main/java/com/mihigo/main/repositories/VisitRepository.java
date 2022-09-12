package com.mihigo.main.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.Visitors;
import com.mihigo.main.payloads.TopVisitings;
import com.mihigo.main.payloads.VisitTable;

@Repository
public interface VisitRepository extends JpaRepository<Visitors, Long> {

	List<Visitors> findAllByOrderByIdDesc();

	List<Visitors> findAllBySite(Site site);

	long countAllBySite(Site site);

	@Query("SELECT COUNT(v.id) FROM Visitors v WHERE DATE(v.date) =DATE(NOW()) AND site = :site")
	long countvisitorsToday(@Param("site") Site site);

	@Query("SELECT COUNT(v.id) FROM Visitors v WHERE DATE(v.date) =DATE(NOW())")
	long countvisitorsToday();

	@Query("SELECT COUNT(v.id) FROM Visitors v WHERE MONTH(v.date) =MONTH(NOW()) AND site = :site")
	long countvisitorsMonth(@Param("site") Site site);

	@Query("SELECT COUNT(v.id) FROM Visitors v WHERE MONTH(v.date) =MONTH(NOW())")
	long countvisitorsMonth();

	@Query("SELECT COUNT(v.id) FROM Visitors v WHERE YEAR(v.date) =YEAR(NOW()) AND site = :site")
	long countvisitorsYear(@Param("site") Site site);

	@Query("SELECT COUNT(v.id) FROM Visitors v WHERE YEAR(v.date) =YEAR(NOW())")
	long countvisitorsYear();

	@Query("Select new com.mihigo.main.payloads.TopVisitings(s.name,COUNT(v.id),SUM(v.paidAmount)) FROM Visitors v JOIN Site s ON v.site = s.id WHERE DAY(v.date) = DAY(NOW()) GROUP BY v.site ORDER BY SUM(v.paidAmount) DESC")
	List<TopVisitings> topVisitingDay();

	@Query("Select new com.mihigo.main.payloads.TopVisitings(s.name,COUNT(v.id),SUM(v.paidAmount)) FROM Visitors v JOIN Site s ON v.site = s.id WHERE MONTH(v.date) = MONTH(NOW()) GROUP BY v.site ORDER BY SUM(v.paidAmount) DESC")
	List<TopVisitings> topVisitingMonth();

	@Query("Select new com.mihigo.main.payloads.TopVisitings(s.name,COUNT(v.id),SUM(v.paidAmount)) FROM Visitors v JOIN Site s ON v.site = s.id WHERE YEAR(v.date) = YEAR(NOW()) GROUP BY v.site ORDER BY SUM(v.paidAmount) DESC")
	List<TopVisitings> topVisitingYear();

	@Query("SELECT new com.mihigo.main.payloads.VisitTable(vs.names, vs.gender, vs.date,s.price) FROM Visitors vs JOIN Site s ON s.id = vs.site WHERE s.refKey = :siteRefKey AND (vs.date BETWEEN :fromm AND :too) ORDER BY vs.date DESC")
	List<VisitTable> visitorsByPeriodAndSite(@Param("fromm") Date fromm, @Param("too") Date too,
			@Param("siteRefKey") String siteRefKey);

	@Query("SELECT SUM(v.id) FROM Visitors v WHERE DATE(v.date) =DATE(NOW()) AND site = :site")
	String sumSiteRevenueToday(@Param("site") Site site);

	@Query("SELECT SUM(v.id) FROM Visitors v WHERE MONTH(v.date) =MONTH(NOW()) AND site = :site")
	String sumSiteRevenueMonth(@Param("site") Site site);

	@Query("SELECT SUM(v.id) FROM Visitors v WHERE YEAR(v.date) =YEAR(NOW()) AND site = :site")
	String sumSiteRevenueYear(@Param("site") Site site);

}
