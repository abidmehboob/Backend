package com.alseyahat.app.feature.deal.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.deal.repository.DealRepository;
import com.alseyahat.app.feature.deal.repository.entity.Deal;
import com.alseyahat.app.feature.deal.service.DealService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DealServiceImpl implements DealService{

	DealRepository dealRepository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Deal save(Deal entity) {
		return dealRepository.save(entity);
	}

	@Override
	public Deal findOne(Predicate predicate) {
		return dealRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<Deal> find_One(Predicate predicate) {
		return dealRepository.findOne(predicate);
	}

	@Override
	public Page<Deal> findAll(Predicate predicate, Pageable pageable) {
		return dealRepository.findAll(predicate, pageable);
	}

}
