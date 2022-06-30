package com.mihigo.main.service.reports;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Report;
import com.mihigo.main.models.Users;
import com.mihigo.main.repositories.ReportRepository;
import com.mihigo.main.repositories.UserRepository;
import com.mihigo.main.tools.Randomazation;

@Service
public class ReportServiceImplementation implements ReportServiceInterface {

	@Autowired
	private UserRepository user_repo;

	@Autowired
	private ReportRepository report_repo;

	@Autowired
	private Randomazation rand;

	@Override
	public Report createReport(String userRef, String report) {
		try {

			if (userRef.isBlank() || report.isBlank()) {
				throw new RuntimeException("Please fill all requirements");
			}
			Users uz = user_repo.searchByrefKey(userRef);

			if (uz == null) {
				throw new RuntimeException("Invalid user");
			}

			return report_repo.saveAndFlush(new Report(uz, report, rand.random(16)));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Report createReport(String userRef, String report, MultipartFile document) {
		try {
			String docu = org.springframework.util.StringUtils.cleanPath(document.getOriginalFilename());

			if (docu.contains("..")) {
				throw new RuntimeException("Invalid document");
			}

			if (userRef.isBlank() || report.isBlank()) {
				throw new RuntimeException("Please fill all requirements");
			}
			Users uz = user_repo.searchByrefKey(userRef);

			if (uz == null) {
				throw new RuntimeException("Invalid user");
			}

			String doc = Base64.getEncoder().encodeToString(document.getBytes());

			return report_repo.saveAndFlush(new Report(uz, report, doc, rand.random(16)));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
