package com.alseyahat.app.feature.hotel.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.hotel.repository.entity.HotelBooking;
import com.querydsl.core.types.Predicate;

public interface HotelBookingService {
	HotelBooking save(final HotelBooking entity);

	HotelBooking findOne(final Predicate predicate);

    Optional<HotelBooking> find_One(final Predicate predicate);

    Page<HotelBooking> findAll(final Predicate predicate, final Pageable pageable);

}