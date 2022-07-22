
package com.mihigo.main.service.visitsite;

import java.util.List;

import com.mihigo.main.models.Gender;
import com.mihigo.main.models.VisitSite;
import com.mihigo.main.payloads.VisitTable;

public interface VisitInterface {

	VisitSite visitByCamera(String key);

	VisitSite visitSite(String email, String phone, String province, String district, String sector, String names,
			Gender gender, String siteRef);

	List<VisitTable> findAllVisitors();

	List<VisitTable> findAllbyField(String field);

	List<VisitTable> findAllWithPagination(int offset, int pagesize);

	long countAll(String period);

	long countAll(String period, String refKey);

	double countRevenue(String period);

	double countRevenue(String period, String refKey);

}
