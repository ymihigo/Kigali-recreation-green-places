package com.mihigo.main.service.sectors;


import java.util.List;

import com.mihigo.main.models.Sectors;

public interface SectorServices {

	List<Sectors> getSectorsByDistrictsId(int id);
}
