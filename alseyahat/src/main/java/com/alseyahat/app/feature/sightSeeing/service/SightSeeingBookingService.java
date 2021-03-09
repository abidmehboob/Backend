package com.alseyahat.app.feature.sightSeeing.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeingBooking;
import com.querydsl.core.types.Predicate;

public interface SightSeeingBookingService {

	SightSeeingBooking save(final SightSeeingBooking entity);

	SightSeeingBooking findOne(final Predicate predicate);

    Optional<SightSeeingBooking> find_One(final Predicate predicate);

    Page<SightSeeingBooking> findAll(final Predicate predicate, final Pageable pageable);


}
