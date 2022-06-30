package com.mihigo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
