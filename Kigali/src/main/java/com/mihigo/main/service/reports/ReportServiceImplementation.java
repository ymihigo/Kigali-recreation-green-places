package com.mihigo.main.service.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mihigo.main.models.Report;
import com.mihigo.main.models.Users;
import com.mihigo.main.payloads.ReportPayload;
import com.mihigo.main.payloads.SitePayload;
import com.mihigo.main.payloads.UserOutputPayload;
import com.mihigo.main.payloads.UserPayloadRef;
import com.mihigo.main.repositories.ReportRepository;
import com.mihigo.main.repositories.UserRepository;
import com.mihigo.main.service.users.UserServices;
import com.mihigo.main.tools.Randomazation;

@Service
public class ReportServiceImplementation implements ReportServiceInterface {

	@Autowired
	private UserRepository user_repo;

	@Autowired
	private ReportRepository report_repo;

	@Autowired
	private Randomazation rand;

	@Autowired
	private UserServices userservice;

	@Override
	public Report createReport(String title, String userRef, String report) {
		try {
			if (userRef.isBlank() || report.isBlank() || title.isBlank()) {
				throw new RuntimeException("Please fill all requirements");
			}
			Users uz = user_repo.searchByrefKey(userRef);

			if (uz == null) {
				throw new RuntimeException("Invalid user");
			}

			return report_repo.saveAndFlush(new Report(title, uz, report, rand.random(16)));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Report createReport(String title, String userRef, String report, MultipartFile document) {
		try {
			String docu = org.springframework.util.StringUtils.cleanPath(document.getOriginalFilename());

			if (docu.contains("..")) {
				throw new RuntimeException("Invalid document");
			}
			String extension = StringUtils.getFilenameExtension(document.getOriginalFilename());

			if (!extension.endsWith("docx")) {
				throw new RuntimeException("Only word document is allowed here");
			}

			if (userRef.isBlank() || report.isBlank() || title.isBlank()) {
				throw new RuntimeException("Please fill all requirements");
			}
			Users uz = user_repo.searchByrefKey(userRef);

			if (uz == null) {
				throw new RuntimeException("Invalid user");
			}

			String doc = Base64.getEncoder().encodeToString(document.getBytes());

			return report_repo.saveAndFlush(new Report(title, uz, report, doc, rand.random(16)));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<ReportPayload> getAllReport(String from, String to) {
		try {

			Date frm = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(from);
			Date too = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(to);
			List<Report> all_repos = report_repo.findAllByPeriod(frm, too);

			List<ReportPayload> resu = new ArrayList<>();

			all_repos.forEach(x -> {

				resu.add(new ReportPayload(x.getReportTitle(),
						new UserOutputPayload(x.getUzer().getEmail(), x.getUzer().getPhone(), x.getUzer().getProvince(),
								x.getUzer().getDistrict(), x.getUzer().getSector(), x.getUzer().getNames(),
								x.getUzer().getGender(), x.getUzer().getRole(), x.getUzer().getUsername(),
								x.getUzer().getRefKey(), x.getUzer().getRegDate(), x.getUzer().getStatus()),
						new SitePayload(x.getUzer().getSite().getEmail(), x.getUzer().getSite().getPhone(),
								x.getUzer().getSite().getProvince(), x.getUzer().getSite().getDistrict(),
								x.getUzer().getSite().getSector(), x.getUzer().getSite().getName(),
								x.getUzer().getSite().getStatus().toString(), x.getUzer().getSite().getPrice(),
								x.getUzer().getSite().getRefKey()),
						x.getDetail(), x.getDoc(), x.getDoneOn(), x.getRefKey()));
			});
			return resu;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<ReportPayload> getAllReport(String from, String to, String userRefKey) {
		try {

			Users u = userservice.getUserByRefKey(userRefKey);

			Date frm = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(from);
			Date too = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(to);
			List<Report> all_repos = report_repo.findAllByPeriod(frm, too, u);

			List<ReportPayload> resu = new ArrayList<>();

			all_repos.forEach(x -> {

				resu.add(new ReportPayload(x.getReportTitle(),
						new UserOutputPayload(x.getUzer().getEmail(), x.getUzer().getPhone(), x.getUzer().getProvince(),
								x.getUzer().getDistrict(), x.getUzer().getSector(), x.getUzer().getNames(),
								x.getUzer().getGender(), x.getUzer().getRole(), x.getUzer().getUsername(),
								x.getUzer().getRefKey(), x.getUzer().getRegDate(), x.getUzer().getStatus()),
						new SitePayload(x.getUzer().getSite().getEmail(), x.getUzer().getSite().getPhone(),
								x.getUzer().getSite().getProvince(), x.getUzer().getSite().getDistrict(),
								x.getUzer().getSite().getSector(), x.getUzer().getSite().getName(),
								x.getUzer().getSite().getStatus().toString(), x.getUzer().getSite().getPrice(),
								x.getUzer().getSite().getRefKey()),
						x.getDetail(), x.getDoc(), x.getDoneOn(), x.getRefKey()));
			});
			return resu;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<ReportPayload> getAllReports() {
		try {
			List<Report> all_Repo = report_repo.findAll(Sort.by(Sort.Direction.DESC, "doneOn"));
			List<ReportPayload> resu = new ArrayList<>();
			all_Repo.forEach(x -> {
				UserOutputPayload uza = new UserOutputPayload(x.getUzer().getEmail(), x.getUzer().getPhone(),
						x.getUzer().getProvince(), x.getUzer().getDistrict(), x.getUzer().getSector(),
						x.getUzer().getNames(), x.getUzer().getGender(), x.getUzer().getRole(),
						x.getUzer().getUsername(), x.getUzer().getRefKey(), x.getUzer().getRegDate(),
						x.getUzer().getStatus());
				SitePayload sitee = new SitePayload(x.getUzer().getSite().getEmail(), x.getUzer().getSite().getPhone(),
						x.getUzer().getSite().getProvince(), x.getUzer().getSite().getDistrict(),
						x.getUzer().getSite().getSector(), x.getUzer().getSite().getName(),
						x.getUzer().getSite().getStatus().toString(), x.getUzer().getSite().getPrice(),
						x.getUzer().getSite().getRefKey());
				resu.add(new ReportPayload(x.getReportTitle(), uza, sitee, x.getDetail(), x.getDoc(), x.getDoneOn(),
						x.getRefKey()));
			});
			return resu;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<ReportPayload> getAllReports(String userRefKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
