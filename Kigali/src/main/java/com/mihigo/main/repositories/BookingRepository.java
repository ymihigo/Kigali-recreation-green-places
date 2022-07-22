package com.mihigo.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihigo.main.models.BookingSite;

@Repository
public interface BookingRepository extends JpaRepository<BookingSite, Long> {

}
