package com.mihigo.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Sectors;


@Repository
public interface SectorsRepo extends JpaRepository<Sectors, Integer> {

	@Query("FROM Sectors WHERE district_id = :pid")
	List<Sectors> getSectorsByDistrict(@Param("pid") int pid);
}
