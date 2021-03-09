package com.alseyahat.app.feature.deal.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.deal.repository.DealBookingRepository;
import com.alseyahat.app.feature.deal.repository.entity.DealBooking;
import com.alseyahat.app.feature.deal.service.DealBookingService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DealBookingServiceImpl implements DealBookingService{

	DealBookingRepository dealBookingRepository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public DealBooking save(DealBooking entity) {
		return dealBookingRepository.save(entity);
	}

	@Override
	public DealBooking findOne(Predicate predicate) {
		return dealBookingRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.dealBooking_entity_not_found"));
	}

	@Override
	public Optional<DealBooking> find_One(Predicate predicate) {
		return dealBookingRepository.findOne(predicate);
	}

	@Override
	public Page<DealBooking> findAll(Predicate predicate, Pageable pageable) {
		return dealBookingRepository.findAll(predicate, pageable);
	}

}
