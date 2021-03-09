package com.alseyahat.app.feature.ride.service.impl;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.ride.repository.BookingRepository;
import com.alseyahat.app.feature.ride.repository.entity.Booking;
import com.alseyahat.app.feature.ride.service.BookingService;
import com.querydsl.core.types.Predicate;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingServiceImpl implements BookingService {

	BookingRepository bookingRepository;
	
	@Override
	public Booking save(Booking entity) {
		return bookingRepository.save(entity);
	}

	@Override
	public Booking findOne(Predicate predicate) {
		return bookingRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<Booking> find_One(Predicate predicate) {
		return bookingRepository.findOne(predicate);
	}

	@Override
	public Page<Booking> findAll(Predicate predicate, Pageable pageable) {
		return bookingRepository.findAll(predicate, pageable);
	}


}