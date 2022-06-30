package com.mihigo.main;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mihigo.main.models.Site;
import com.mihigo.main.models.VisitSite;
import com.mihigo.main.payloads.VisitTable;
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

	@Test
	void contextLoads() {
		Site sss = siteInter.searchByReferenceKey("QHWl6pvA5Rr0ozMs");
		
		List<VisitSite> kiki=virepo.findAllBySite(sss);
		
		
	}

}
