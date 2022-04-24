package com.mihigo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Provinces;

@Repository
public interface ProvinceRepo extends JpaRepository<Provinces, Integer> {

}
