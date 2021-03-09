package com.alseyahat.app.feature.ride.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.ride.repository.entity.RideRequests;
import com.querydsl.core.types.Predicate;

public interface RideRequestsService {
	RideRequests save(final RideRequests entity);

	RideRequests findOne(final Predicate predicate);

    Optional<RideRequests> find_One(final Predicate predicate);

    Page<RideRequests> findAll(final Predicate predicate, final Pageable pageable);

}