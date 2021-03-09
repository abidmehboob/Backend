package com.alseyahat.app.feature.vehicle.hired.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHired;
import com.querydsl.core.types.Predicate;

public interface PrivateHiredService {
	PrivateHired save(final PrivateHired entity);

	PrivateHired findOne(final Predicate predicate);

    Optional<PrivateHired> find_One(final Predicate predicate);

    Page<PrivateHired> findAll(final Predicate predicate, final Pageable pageable);


}
