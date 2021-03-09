package com.alseyahat.app.feature.hotel.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.hotel.repository.HotelFacilityRepository;
import com.alseyahat.app.feature.hotel.repository.entity.HotelFacility;
import com.alseyahat.app.feature.hotel.service.HotelFacilityService;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelFacilityServiceImpl implements HotelFacilityService{
	
	HotelFacilityRepository hotelFacilityRepository;

	@Override
	public HotelFacility save(HotelFacility entity) {
		return hotelFacilityRepository.save(entity);
	}

	@Override
	public HotelFacility findOne(Predicate predicate) {
		return hotelFacilityRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<HotelFacility> find_One(Predicate predicate) {
		return hotelFacilityRepository.findOne(predicate);
	}

	@Override
	public Page<HotelFacility> findAll(Predicate predicate, Pageable pageable) {
		return hotelFacilityRepository.findAll(predicate, pageable);
	}

}
