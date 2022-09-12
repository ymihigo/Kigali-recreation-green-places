package com.mihigo.main.service.booking;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mihigo.main.models.BookingSite;
import com.mihigo.main.models.Site;
import com.mihigo.main.repositories.BookingRepository;
import com.mihigo.main.service.site.SiteInterface;
import com.mihigo.main.tools.Randomazation;

@Service
public class BookingServiceImplementation implements BookingService {

	@Autowired
	private SiteInterface siteInterface;

	@Autowired
	private BookingRepository bookRepo;

	Randomazation rand = new Randomazation();

	@Override
	public BookingSite createBooking(String name, String refKey, String phone, int num, Date date, String email) {
		try {
			if (name.isBlank() || refKey.isBlank() || phone.isBlank()) {
				throw new RuntimeException("PLease fill all requirememnts");
			}
			Site sit = siteInterface.searchByReferenceKey(refKey);

			if (date.before(new Date())) {
				throw new RuntimeException("Invalid date");
			}
			String r = rand.random(16);
			if (bookRepo.findByRefKey(r) != null) {
				createBooking(name, refKey, phone, num, date, email);
			}

			return bookRepo.save(new BookingSite(date, sit, r, num, name, phone, email));
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<BookingSite> findBookingBySite(String siteRefKey) {
		try {
			Site sit = siteInterface.searchByReferenceKey(siteRefKey);

			return bookRepo.findBySite(sit);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<BookingSite> findbookingByApproved(String siteRefKey, boolean status) {
		try {
			Site sit = siteInterface.searchByReferenceKey(siteRefKey);
			return bookRepo.findBySiteAndApproved(sit, status);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public BookingSite changeStatus(String bookingRefKey) {
		try {
			BookingSite boo = findByRefKey(bookingRefKey);
			if (boo.isApproved() == true) {
				boo.setApproved(false);
			} else {
				boo.setApproved(true);
			}
			return bookRepo.saveAndFlush(boo);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public BookingSite findByRefKey(String refKey) {
		try {
			if (refKey.isBlank()) {
				throw new RuntimeException("please enter refKey");
			}
			BookingSite boo = bookRepo.findByRefKey(refKey);

			if (boo == null) {
				throw new RuntimeException("No booking found");
			}
			return boo;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public long countBooking(String period, String refKey) {
		try {

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return 0;
	}
}
