package com.alseyahat.app.feature.hotel.facade.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.hotel.dto.RoomTypeCreateRequest;
import com.alseyahat.app.feature.hotel.dto.RoomTypeCreateResponse;
import com.alseyahat.app.feature.hotel.dto.RoomTypeDetailResponse;
import com.alseyahat.app.feature.hotel.dto.RoomTypeUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.RoomTypeUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.RoomTypeFacade;
import com.alseyahat.app.feature.hotel.repository.entity.QRoomType;
import com.alseyahat.app.feature.hotel.repository.entity.RoomType;
import com.alseyahat.app.feature.hotel.service.RoomTypeService;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomTypeFacadeImpl implements RoomTypeFacade {
    
	RoomTypeService roomTypeService;
	
    ModelMapper modelMapper;
    
	@Override
	public void deleteRoomType(String roomTypeId) {
		RoomType roomType = roomTypeService.findOne(QRoomType.roomType.roomTypeId.eq(roomTypeId));
		if (roomType.isEnabled())
			roomType.setEnabled(Boolean.FALSE);
		else
			roomType.setEnabled(Boolean.TRUE);

		roomTypeService.save(roomType);
		log.trace("Review deleted with id [{}]", roomTypeId);
	}

	@Override
	public RoomTypeDetailResponse findRoomTypeId(String roomTypeId) {
		RoomType roomType = roomTypeService.findOne(QRoomType.roomType.roomTypeId.eq(roomTypeId));
		return buildRoomTypeDetailResponse(roomType);
	}

	@Override
	public RoomTypeCreateResponse createRoomType(RoomTypeCreateRequest request) {
		final RoomType savedRoomType = roomTypeService.save(buildRoomType(request));
		return RoomTypeCreateResponse.builder().roomTypeId(savedRoomType.getRoomTypeId()).build();
	}

	@Override
	public RoomTypeUpdateResponse updateRoomType(RoomTypeUpdateRequest request) {
		RoomType roomType = roomTypeService.findOne(QRoomType.roomType.roomTypeId.eq(request.getRoomTypeId()));
		final RoomType savedRoomType = roomTypeService.save(buildRoomType(roomType, request));
		return RoomTypeUpdateResponse.builder().roomTypeId(savedRoomType.getRoomTypeId()).build();
	}

	@Override
	public Page<RoomTypeDetailResponse> findAllRoomType(Predicate predicate, Pageable pageable) {
		return roomTypeService.findAll(predicate, pageable).map(this::buildRoomTypeDetailResponse);
	}
	
	private RoomTypeDetailResponse buildRoomTypeDetailResponse(final RoomType roomType) {
		RoomTypeDetailResponse response = new RoomTypeDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<RoomType, RoomTypeDetailResponse> typeMap = modelMapper.typeMap(RoomType.class, RoomTypeDetailResponse.class);
		typeMap.map(roomType, response);
	
		return response;
	}
	
	private RoomType buildRoomType(final RoomTypeCreateRequest request) {
		RoomType roomType = new RoomType();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<RoomTypeCreateRequest, RoomType> typeMap = modelMapper.typeMap(RoomTypeCreateRequest.class, RoomType.class);
		typeMap.addMappings(mapper -> mapper.skip(RoomType::setRoomTypeId));
		typeMap.addMappings(mapper -> mapper.skip(RoomType::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(RoomType::setLastUpdated));
		typeMap.map(request, roomType);
		
		return roomType;
	}
	
	private RoomType buildRoomType(RoomType roomType, final RoomTypeUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<RoomTypeUpdateRequest, RoomType> typeMap = modelMapper.typeMap(RoomTypeUpdateRequest.class, RoomType.class);
		typeMap.addMappings(mapper -> mapper.skip(RoomType::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(RoomType::setLastUpdated));
		typeMap.map(request, roomType);
		// category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return roomType;
	}

}
