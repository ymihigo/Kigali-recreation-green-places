package com.mihigo.main.service.visitsite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.mihigo.main.models.Gender;
import com.mihigo.main.models.Site;
import com.mihigo.main.models.VisitSite;
import com.mihigo.main.payloads.VisitTable;
import com.mihigo.main.repositories.SiteRepository;
import com.mihigo.main.repositories.VisitRepository;

@Service
public class VisitInterfaceImplementation implements VisitInterface {

	@Autowired
	private SiteRepository sr;

	@Autowired
	private VisitRepository vr;

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
				output.add(new VisitTable(x.getNames(), x.getGender(), x.getDate()));
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
				output.add(new VisitTable(x.getNames(), x.getGender(), x.getDate()));
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
				output.add(new VisitTable(x.getNames(), x.getGender(), x.getDate()));
			});
			return output;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
