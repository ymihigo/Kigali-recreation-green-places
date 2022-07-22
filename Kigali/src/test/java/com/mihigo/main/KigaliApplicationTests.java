package com.mihigo.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.VisitSite;
import com.mihigo.main.payloads.VisitTable;
import com.mihigo.main.repositories.ReportRepository;
import com.mihigo.main.repositories.SiteRepository;
import com.mihigo.main.repositories.UserRepository;
import com.mihigo.main.repositories.VisitRepository;
import com.mihigo.main.service.site.SiteInterface;
import com.mihigo.main.service.visitsite.VisitInterface;

@SpringBootTest
class KigaliApplicationTests {

	@Autowired
	private SiteRepository sire;

	@Autowired
	private UserRepository ur;

	@Autowired
	private VisitInterface vi;

	@Autowired
	private VisitRepository virepo;

	@Autowired
	private SiteInterface siteInter;

	@Autowired
	private VisitInterface viv;

	@Autowired
	private ReportRepository reporepo;

	@Test
	void contextLoads() throws ParseException {

		Date from = new SimpleDateFormat("dd/MM/yyy HH").parse("14/06/2010 01:32");
		Date to = new Date();

		System.out.println(reporepo.findAllByPeriod(from, to).size());
//		System.out.println(from);
	}

}
