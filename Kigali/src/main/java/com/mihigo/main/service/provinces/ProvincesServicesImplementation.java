package com.mihigo.main.service.provinces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mihigo.main.models.Provinces;
import com.mihigo.main.repositories.ProvinceRepo;


@Service
public class ProvincesServicesImplementation implements ProvinceService {

	@Autowired
	private ProvinceRepo prepo;
	
	@Override
	public List<Provinces> allProvinces() {
		try {
			return prepo.findAll();
		}catch(Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Provinces getProvinceById(int id) {
		try {
			Optional<Provinces> prov=prepo.findById(id);
			if(prov.isEmpty()) {
				throw new RuntimeException("Invalid province id");
			}
			return prov.get();
		}catch(Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
