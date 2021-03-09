package com.alseyahat.app.feature.hotel.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.hotel.repository.entity.RoomType;
import com.querydsl.core.types.Predicate;

public interface RoomTypeService {
	RoomType save(final RoomType entity);

	RoomType findOne(final Predicate predicate);

    Optional<RoomType> find_One(final Predicate predicate);

    Page<RoomType> findAll(final Predicate predicate, final Pageable pageable);

}