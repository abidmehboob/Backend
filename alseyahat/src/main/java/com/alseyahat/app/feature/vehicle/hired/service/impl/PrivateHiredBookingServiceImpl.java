package com.alseyahat.app.feature.vehicle.hired.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.vehicle.hired.repository.PrivateHiredBookingRepository;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHiredBooking;
import com.alseyahat.app.feature.vehicle.hired.service.PrivateHiredBookingService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PrivateHiredBookingServiceImpl implements PrivateHiredBookingService {

	PrivateHiredBookingRepository privateHiredBookingRepository;
	
	@Override
	public PrivateHiredBooking save(PrivateHiredBooking entity) {
		return privateHiredBookingRepository.save(entity);
	}

	@Override
	public PrivateHiredBooking findOne(Predicate predicate) {
		return privateHiredBookingRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.privateHiredBooking_entity_not_found"));
	}

	@Override
	public Optional<PrivateHiredBooking> find_One(Predicate predicate) {
		return privateHiredBookingRepository.findOne(predicate);
	}

	@Override
	public Page<PrivateHiredBooking> findAll(Predicate predicate, Pageable pageable) {
		return privateHiredBookingRepository.findAll(predicate, pageable);
	}

}

