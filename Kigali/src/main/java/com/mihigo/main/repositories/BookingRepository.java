package com.mihigo.main.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.BookingSite;
import com.mihigo.main.models.Site;

@Repository
public interface BookingRepository extends JpaRepository<BookingSite, Long> {

	BookingSite findByRefKey(String refKey);

	List<BookingSite> findBySite(Site site);

	List<BookingSite> findByVisitDateTime(Date datetime);

	List<BookingSite> findBySiteAndApproved(Site site, boolean status);

	long countBySiteAndApproved(Site site, boolean status);

	long countByVisitDateTime(Date datetime);

	@Query("SELECT COUNT(v.id) FROM BookingSite v WHERE DATE(v.visitDateTime)= DATE(NOW()) AND site = :site")
	long countBookingToday(@Param("site") Site si);

	@Query("SELECT COUNT(v.id) FROM BookingSite v WHERE MONTH(v.visitDateTime)=MONTH(NOW()) AND site = :site")
	long countBookingMonth(@Param("site") Site si);

	@Query("SELECT COUNT(v.id) FROM BookingSite v WHERE YEAR(v.visitDateTime)=YEAR(NOW()) AND site =  :site")
	long countBookingYear(@Param("site") Site si);

}
