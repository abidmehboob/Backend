package com.alseyahat.app.feature.hotel.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.hotel.repository.HotelBookingRepository;
import com.alseyahat.app.feature.hotel.repository.entity.HotelBooking;
import com.alseyahat.app.feature.hotel.service.HotelBookingService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelBookingServiceImpl implements HotelBookingService{
	
	HotelBookingRepository hotelBookingRepository;

	@Override
	public HotelBooking save(HotelBooking entity) {
		return hotelBookingRepository.save(entity);
	}

	@Override
	public HotelBooking findOne(Predicate predicate) {
		return hotelBookingRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<HotelBooking> find_One(Predicate predicate) {
		return hotelBookingRepository.findOne(predicate);
	}

	@Override
	public Page<HotelBooking> findAll(Predicate predicate, Pageable pageable) {
		return hotelBookingRepository.findAll(predicate, pageable);
	}

}
