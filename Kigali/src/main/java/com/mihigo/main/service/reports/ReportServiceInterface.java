package com.mihigo.main.service.reports;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Report;
import com.mihigo.main.payloads.ReportPayload;

public interface ReportServiceInterface {

	Report createReport(String userRef, String report);

	Report createReport(String userRef, String report, MultipartFile document);

	List<ReportPayload> getAllReport(String period);

	List<ReportPayload> getAllReport(String period, String siteRefKey);
}
