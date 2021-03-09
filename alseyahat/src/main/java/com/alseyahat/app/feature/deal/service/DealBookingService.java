package com.alseyahat.app.feature.deal.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.deal.repository.entity.DealBooking;
import com.querydsl.core.types.Predicate;

public interface DealBookingService {
	DealBooking save(final DealBooking entity);

	DealBooking findOne(final Predicate predicate);

    Optional<DealBooking> find_One(final Predicate predicate);

    Page<DealBooking> findAll(final Predicate predicate, final Pageable pageable);


}
