package com.alseyahat.app.feature.deal.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.deal.repository.entity.Deal;
import com.querydsl.core.types.Predicate;

public interface DealService {
	
	Deal save(final Deal entity);

	Deal findOne(final Predicate predicate);

    Optional<Deal> find_One(final Predicate predicate);

    Page<Deal> findAll(final Predicate predicate, final Pageable pageable);


}
