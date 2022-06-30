package com.mihigo.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Districts;

@Repository
public interface DistrictsRepo extends JpaRepository<Districts, Integer> {

	@Query("FROM Districts WHERE province_id = :pid")
	List<Districts> getDistrictsByProvinces(@Param("pid") int pid);

	Districts findByDistrict(String district);
}
