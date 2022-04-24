package com.mihigo.main.service.districts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mihigo.main.models.Districts;
import com.mihigo.main.repositories.DistrictsRepo;

@Service
public class DistrictsServicesImplementation implements DistrictsServices {

	@Autowired
	private DistrictsRepo dr;

	@Override
	public List<Districts> getDistrictsById(int provinceId) {
		try {
			if (provinceId < 1 || provinceId > 5) {
				throw new RuntimeException("Invalid province id");
			}
			List<Districts> dis = dr.getDistrictsByProvinces(provinceId);
			return dis;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
