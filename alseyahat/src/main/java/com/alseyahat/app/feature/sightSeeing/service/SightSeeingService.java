package com.alseyahat.app.feature.sightSeeing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeing;
import com.querydsl.core.types.Predicate;

public interface SightSeeingService {
	SightSeeing save(final SightSeeing entity);

	SightSeeing findOne(final Predicate predicate);

    Optional<SightSeeing> find_One(final Predicate predicate);

    Page<SightSeeing> findAll(final Predicate predicate, final Pageable pageable);


}