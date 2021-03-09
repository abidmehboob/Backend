package com.alseyahat.app.feature.ride.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.ride.repository.RideRequestsRepository;
import com.alseyahat.app.feature.ride.repository.entity.RideRequests;
import com.alseyahat.app.feature.ride.service.RideRequestsService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RideRequestsServiceImpl implements RideRequestsService {

	RideRequestsRepository rideRequestsRepository;
	
	@Override
	public RideRequests save(RideRequests entity) {
		return rideRequestsRepository.save(entity);
	}

	@Override
	public RideRequests findOne(Predicate predicate) {
		return rideRequestsRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<RideRequests> find_One(Predicate predicate) {
		return rideRequestsRepository.findOne(predicate);
	}


	@Override
	public Page<RideRequests> findAll(Predicate predicate, Pageable pageable) {
		return rideRequestsRepository.findAll(predicate, pageable);
	}

}
