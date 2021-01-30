package com.alseyahat.app.feature.hotel.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.hotel.repository.HotelRepository;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.alseyahat.app.feature.hotel.service.HotelService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelServiceImpl implements HotelService{
	
	HotelRepository hotelRepository;

	@Override
	public Hotel save(Hotel entity) {
		return hotelRepository.save(entity);
	}

	@Override
	public Hotel findOne(Predicate predicate) {
		return hotelRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<Hotel> find_One(Predicate predicate) {
		return hotelRepository.findOne(predicate);
	}

	@Override
	public Page<Hotel> findAll(Predicate predicate, Pageable pageable) {
		return hotelRepository.findAll(predicate, pageable);
	}

}
