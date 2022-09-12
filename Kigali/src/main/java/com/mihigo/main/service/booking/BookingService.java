package com.mihigo.main.service.booking;

import java.util.Date;
import java.util.List;

import com.mihigo.main.models.BookingSite;

public interface BookingService {
	BookingSite createBooking(String name, String refKey, String phone, int num, Date date, String email);

	List<BookingSite> findBookingBySite(String siteRefKey);

	List<BookingSite> findbookingByApproved(String siteRefKey, boolean status);

	BookingSite changeStatus(String bookingRefKey);

	BookingSite findByRefKey(String refKey);

	long countBooking(String period,String refKey);
}
