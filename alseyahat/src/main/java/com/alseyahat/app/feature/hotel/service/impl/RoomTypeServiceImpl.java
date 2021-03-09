package com.alseyahat.app.feature.hotel.service.impl;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.hotel.repository.RoomTypeRepository;
import com.alseyahat.app.feature.hotel.repository.entity.RoomType;
import com.alseyahat.app.feature.hotel.service.RoomTypeService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomTypeServiceImpl implements RoomTypeService{
	
	RoomTypeRepository roomTypeRepository;

	@Override
	public RoomType save(RoomType entity) {
		return roomTypeRepository.save(entity);
	}

	@Override
	public RoomType findOne(Predicate predicate) {
		return roomTypeRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<RoomType> find_One(Predicate predicate) {
		return roomTypeRepository.findOne(predicate);
	}

	@Override
	public Page<RoomType> findAll(Predicate predicate, Pageable pageable) {
		return roomTypeRepository.findAll(predicate, pageable);
	}

}
