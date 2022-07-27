package com.mihigo.main.service.reports;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Report;
import com.mihigo.main.payloads.ReportPayload;

public interface ReportServiceInterface {

	Report createReport(String title, String userRef, String report);

	Report createReport(String title, String userRef, String report, MultipartFile document);

	List<ReportPayload> getAllReports();

	List<ReportPayload> getAllReports(String userRefKey);

	List<ReportPayload> getAllReport(String from, String to);

	List<ReportPayload> getAllReport(String from, String to, String siteRefKey);
}
