package com.alseyahat.app.feature.sightSeeing.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.sightSeeing.repository.SightSeeingRepository;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeing;
import com.alseyahat.app.feature.sightSeeing.service.SightSeeingService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SightSeeingServiceImpl implements SightSeeingService {

	SightSeeingRepository sightSeeingRepository;
	@Override
	public SightSeeing save(SightSeeing entity) {
		return sightSeeingRepository.save(entity);
	}

	@Override
	public SightSeeing findOne(Predicate predicate) {
		return sightSeeingRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<SightSeeing> find_One(Predicate predicate) {
		return sightSeeingRepository.findOne(predicate);
	}

	@Override
	public Page<SightSeeing> findAll(Predicate predicate, Pageable pageable) {
		return sightSeeingRepository.findAll(predicate, pageable);
	}

}
