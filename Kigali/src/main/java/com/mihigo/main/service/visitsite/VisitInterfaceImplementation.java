package com.mihigo.main.service.visitsite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mihigo.main.models.Gender;
import com.mihigo.main.models.Site;
import com.mihigo.main.models.VisitSite;
import com.mihigo.main.payloads.TopVisitings;
import com.mihigo.main.payloads.VisitTable;
import com.mihigo.main.repositories.SiteRepository;
import com.mihigo.main.repositories.VisitRepository;
import com.mihigo.main.service.site.SiteInterface;

@Service
public class VisitInterfaceImplementation implements VisitInterface {

	@Autowired
	private SiteRepository sr;

	@Autowired
	private VisitRepository vr;

	@Autowired
	private SiteInterface site_serv;

	List<VisitTable> output = new ArrayList<>();

	List<VisitSite> li = new ArrayList<>();

	@Override
	public VisitSite visitByCamera(String key) {
		try {
			if (key.isBlank()) {
				throw new RuntimeException("Invalid key");
			}

			Site ss = sr.findByRefKey(key);

			if (ss == null) {
				throw new RuntimeException("Invalid site");
			}

			return vr.saveAndFlush(new VisitSite(ss, ss.getPrice()));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public VisitSite visitSite(String email, String phone, String province, String district, String sector,
			String names, Gender gender, String siteRef) {
		try {
			if (names.isBlank() || gender == null || siteRef.isBlank()) {
				throw new RuntimeException("Please fill all requirements");
			}

			Site ss = sr.findByRefKey(siteRef);

			if (ss == null) {
				throw new RuntimeException("Invalid site");
			}

//			if (email.isEmpty() || email != null) {
//				if (!email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")) {
//					throw new RuntimeException("Invalid email format");
//				}
//			}

//			if (!phone.isBlank()) {
//				if (!phone.matches("^\\+2507[2-3-8-9]{1}\\d{7}$")) {
//					throw new RuntimeException("Invalid phone format");
//				}
//			}

			return vr.saveAndFlush(
					new VisitSite(email, phone, province, district, sector, names, gender, ss, ss.getPrice()));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<VisitTable> findAllVisitors() {
		try {
			li = vr.findAllByOrderByIdDesc();
			output.clear();
			li.forEach(x -> {
				output.add(new VisitTable(x.getNames(), x.getGender(), x.getDate(), x.getPaidAmount()));
			});
			return output;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<VisitTable> findAllbyField(String field) {
		try {
			li = vr.findAll(Sort.by(Sort.Direction.DESC, field));
			li.forEach(x -> {
				output.add(new VisitTable(x.getNames(), x.getGender(), x.getDate(), x.getPaidAmount()));
			});
			return output;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<VisitTable> findAllWithPagination(int offset, int pagesize) {
		try {
			Page<VisitSite> allPageable = vr.findAll(PageRequest.of(offset, pagesize));
			output.clear();
			allPageable.forEach(x -> {
				output.add(new VisitTable(x.getNames(), x.getGender(), x.getDate(), x.getPaidAmount()));
			});
			return output;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public long countAll(String period, String refKey) {
		try {
			Site sit = sr.findByRefKey(refKey);
			if (period.equalsIgnoreCase("today")) {
				return vr.countvisitorsToday(sit);
			} else if (period.equalsIgnoreCase("month")) {
				return vr.countvisitorsMonth(sit);
			} else if (period.equalsIgnoreCase("year")) {
				return vr.countvisitorsYear(sit);
			} else {
				throw new RuntimeException("An error has occured");
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public long countAll(String period) {
		try {
			if (period.equalsIgnoreCase("today")) {
				return vr.countvisitorsToday();
			} else if (period.equalsIgnoreCase("month")) {
				return vr.countvisitorsMonth();
			} else if (period.equalsIgnoreCase("year")) {
				return vr.countvisitorsYear();
			} else {
				throw new RuntimeException("An error has occured");
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public double countRevenue(String period) {
		try {

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return 0;
	}

	@Override
	public double countRevenue(String period, String refKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TopVisitings> topselling(String period) {
		try {
			if (period.isBlank()) {
				throw new RuntimeException("Invalid period");
			}

			String peri = period.toUpperCase();

//			if (peri != "TODAY" || peri != "MONTH" || peri != "YEAR") {
//				throw new RuntimeException("Invalid period period must be ex: TODAY or MONTH or YEAR");
//			}

			if (peri.equalsIgnoreCase("TODAY")) {
				return vr.topVisitingDay();
			}
			if (peri.equalsIgnoreCase("MONTH")) {
				return vr.topVisitingMonth();
			}
			if (peri.equalsIgnoreCase("YEAR")) {
				return vr.topVisitingYear();
			} else {
				return new ArrayList<>();
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<VisitTable> visitorsByPeriodAndSite(String from, String too, String siteRefKey) {
		try {
			Date fromz = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(from);
			Date toz = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(too);

			if (fromz.after(toz)) {
				throw new RuntimeException("Invalid dates");
			}

			site_serv.searchByReferenceKey(siteRefKey);

			return vr.visitorsByPeriodAndSite(fromz, toz, siteRefKey);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
