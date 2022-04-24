package com.mihigo.main.service.provinces;

import java.util.List;

import com.mihigo.main.models.Provinces;

public interface ProvinceService {
	List<Provinces> allProvinces();
	Provinces getProvinceById(int id);
}
