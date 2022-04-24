package com.mihigo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Integer> {
}
