package com.alseyahat.app.feature.sightSeeing.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.sightSeeing.repository.SightSeeingBookingRepository;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeingBooking;
import com.alseyahat.app.feature.sightSeeing.service.SightSeeingBookingService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SightSeeingBookingServiceImpl implements SightSeeingBookingService {

	SightSeeingBookingRepository sightSeeingBookingRepository;
	@Override
	
	public SightSeeingBooking save(SightSeeingBooking entity) {
		return sightSeeingBookingRepository.save(entity);
	}

	@Override
	public SightSeeingBooking findOne(Predicate predicate) {
		return sightSeeingBookingRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<SightSeeingBooking> find_One(Predicate predicate) {
		return sightSeeingBookingRepository.findOne(predicate);
	}

	@Override
	public Page<SightSeeingBooking> findAll(Predicate predicate, Pageable pageable) {
		return sightSeeingBookingRepository.findAll(predicate, pageable);
	}

}
