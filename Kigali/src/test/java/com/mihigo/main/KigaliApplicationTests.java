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
import com.mihigo.main.models.Visitors;
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

//		System.out.println(viv.countRevenue("YEAR", "VWxb2zSELezJf9wA"));

//		Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2022-07-16T15:38");
//		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-19");

//		System.out.println(reporepo.findAllByPeriod(from, to).size());
//		System.out.println(from);
	}

}
