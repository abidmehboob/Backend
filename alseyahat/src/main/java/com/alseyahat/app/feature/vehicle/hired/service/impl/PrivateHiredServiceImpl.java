package com.alseyahat.app.feature.vehicle.hired.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.vehicle.hired.repository.PrivateHiredRepository;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHired;
import com.alseyahat.app.feature.vehicle.hired.service.PrivateHiredService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PrivateHiredServiceImpl implements PrivateHiredService {

	PrivateHiredRepository privateHiredRepository;
	
	@Override
	public PrivateHired save(PrivateHired entity) {
		return privateHiredRepository.save(entity);
	}

	@Override
	public PrivateHired findOne(Predicate predicate) {
		return privateHiredRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<PrivateHired> find_One(Predicate predicate) {
		return privateHiredRepository.findOne(predicate);
	}

	@Override
	public Page<PrivateHired> findAll(Predicate predicate, Pageable pageable) {
		return privateHiredRepository.findAll(predicate, pageable);
	}

}

