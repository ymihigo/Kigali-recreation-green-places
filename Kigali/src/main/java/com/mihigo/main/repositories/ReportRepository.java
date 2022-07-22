package com.mihigo.main.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Report;
import com.mihigo.main.models.Site;
import com.mihigo.main.models.Users;
import com.mihigo.main.payloads.ReportPayload;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	List<Report> findAllByOrderByDoneOnDesc();

	List<Report> findAllByUzer(Users uzer);

	@Query("FROM Report r where r.doneOn BETWEEN :from AND :to")
	List<ReportPayload> findAllByPeriod(@Param("from") Date from, @Param("to") Date to);
//	
//	List<Report> findAllByPeriod(String from, String to, String siteRefKey);
}
