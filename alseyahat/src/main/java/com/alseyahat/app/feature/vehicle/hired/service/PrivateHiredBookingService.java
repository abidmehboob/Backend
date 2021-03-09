package com.alseyahat.app.feature.vehicle.hired.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHiredBooking;
import com.querydsl.core.types.Predicate;

public interface PrivateHiredBookingService {
	PrivateHiredBooking save(final PrivateHiredBooking entity);

	PrivateHiredBooking findOne(final Predicate predicate);

    Optional<PrivateHiredBooking> find_One(final Predicate predicate);

    Page<PrivateHiredBooking> findAll(final Predicate predicate, final Pageable pageable);


}