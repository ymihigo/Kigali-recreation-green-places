package com.mihigo.main.service.districts;

import java.util.*;

import com.mihigo.main.models.Districts;

public interface DistrictsServices {
	List<Districts> getDistrictsById(int provinceId);
}
