package com.alseyahat.app.feature.hotel.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.querydsl.core.types.Predicate;

public interface HotelService {
	Hotel save(final Hotel entity);

	Hotel findOne(final Predicate predicate);

    Optional<Hotel> find_One(final Predicate predicate);

    Page<Hotel> findAll(final Predicate predicate, final Pageable pageable);

}
