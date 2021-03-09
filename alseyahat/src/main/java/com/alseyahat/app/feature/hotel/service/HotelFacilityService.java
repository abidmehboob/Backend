package com.alseyahat.app.feature.hotel.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.hotel.repository.entity.HotelFacility;
import com.querydsl.core.types.Predicate;

public interface HotelFacilityService{
	HotelFacility save(final HotelFacility entity);

	HotelFacility findOne(final Predicate predicate);

    Optional<HotelFacility> find_One(final Predicate predicate);

    Page<HotelFacility> findAll(final Predicate predicate, final Pageable pageable);

}