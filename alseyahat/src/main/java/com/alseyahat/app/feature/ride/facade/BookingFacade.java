package com.alseyahat.app.feature.ride.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.ride.dto.BookingCreateRequest;
import com.alseyahat.app.feature.ride.dto.BookingCreateResponse;
import com.alseyahat.app.feature.ride.dto.BookingDetailResponse;
import com.alseyahat.app.feature.ride.dto.BookingUpdateRequest;
import com.alseyahat.app.feature.ride.dto.BookingUpdateResponse;
import com.querydsl.core.types.Predicate;

public interface BookingFacade {
	
	void deleteBooking(final String bookingId);

	BookingDetailResponse findBookingId(final String bookingId);

	BookingCreateResponse createBooking(final BookingCreateRequest request);

	BookingUpdateResponse updateBooking(final BookingUpdateRequest request);

	Page<BookingDetailResponse> findAllBooking(final Predicate predicate, final Pageable pageable);
}