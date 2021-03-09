package com.alseyahat.app.feature.ride.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.ride.repository.entity.Booking;
import com.querydsl.core.types.Predicate;

public interface BookingService {
	Booking save(final Booking entity);

	Booking findOne(final Predicate predicate);

    Optional<Booking> find_One(final Predicate predicate);

    Page<Booking> findAll(final Predicate predicate, final Pageable pageable);

}
