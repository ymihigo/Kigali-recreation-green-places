package com.mihigo.main.service.sectors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mihigo.main.models.Sectors;
import com.mihigo.main.repositories.SectorsRepo;

@Service
public class SectorServicesImplementation implements SectorServices {

	@Autowired
	private SectorsRepo sr;

	@Override
	public List<Sectors> getSectorsByDistrictsId(int id) {
		try {
			if (id < 1 || id > 30) {
				throw new RuntimeException("Invalid District id");
			}
			return sr.getSectorsByDistrict(id);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
