package com.mihigo.main.service.reports;

import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Report;

public interface ReportServiceInterface {
	Report createReport(String userRef, String report);

	Report createReport(String userRef, String report, MultipartFile document);
}
