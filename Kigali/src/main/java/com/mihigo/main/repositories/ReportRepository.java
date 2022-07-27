package com.mihigo.main.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Report;
import com.mihigo.main.models.Users;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	List<Report> findAllByOrderByDoneOnDesc();

	List<Report> findAllByUzer(Users uzer);

	@Query("FROM Report r where  r.doneOn BETWEEN :from AND :to ORDER BY r.doneOn DESC")
	List<Report> findAllByPeriod(@Param("from") Date from, @Param("to") Date to);

	@Query("FROM Report r where r.uzer= :uzer AND (r.doneOn BETWEEN :from AND :to) ORDER BY r.doneOn DESC")
	List<Report> findAllByPeriod(@Param("from") Date from, @Param("to") Date to, @Param("uzer") Users u);
}
