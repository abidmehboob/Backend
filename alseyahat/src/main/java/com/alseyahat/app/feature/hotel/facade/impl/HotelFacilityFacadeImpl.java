package com.alseyahat.app.feature.hotel.facade.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.hotel.dto.HotelFacilityCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.HotelFacilityFacade;
import com.alseyahat.app.feature.hotel.repository.entity.HotelFacility;
import com.alseyahat.app.feature.hotel.repository.entity.QHotelFacility;
import com.alseyahat.app.feature.hotel.service.HotelFacilityService;
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
public class HotelFacilityFacadeImpl implements HotelFacilityFacade {
	
	HotelFacilityService hotelFacilityService;
	
    ModelMapper modelMapper;
	
	@Override
	public void deleteHotelFacility(String hotelFacilityId) {
		HotelFacility hotelFacility = hotelFacilityService.findOne(QHotelFacility.hotelFacility.hotelFacilityId.eq(hotelFacilityId));
		if (hotelFacility.isEnabled())
			hotelFacility.setEnabled(Boolean.FALSE);
		else
			hotelFacility.setEnabled(Boolean.TRUE);

		hotelFacilityService.save(hotelFacility);
		log.trace("Review deleted with id [{}]", hotelFacilityId);
	}

	@Override
	public HotelFacilityDetailResponse findHotelFacilityId(String hotelFacilityId) {
		HotelFacility hotelFacility = hotelFacilityService.findOne(QHotelFacility.hotelFacility.hotelFacilityId.eq(hotelFacilityId));
		return buildHotelFacilityDetailResponse(hotelFacility);
	}

	@Override
	public HotelFacilityCreateResponse createHotelFacility(HotelFacilityCreateRequest request) {
		final HotelFacility hotelFacility = hotelFacilityService.save(buildHotelFacility(request));
		return HotelFacilityCreateResponse.builder().hotelFacilityId(hotelFacility.getHotelFacilityId()).build();
	}

	@Override
	public HotelFacilityUpdateResponse updateHotelFacility(HotelFacilityUpdateRequest request) {
		HotelFacility hotelFacility = hotelFacilityService.findOne(QHotelFacility.hotelFacility.hotelFacilityId.eq(request.getHotelFacilityId()));
		final HotelFacility savedHotelFacility = hotelFacilityService.save(buildHotelFacility(hotelFacility, request));
		return HotelFacilityUpdateResponse.builder().HotelFacilityId(savedHotelFacility.getHotelFacilityId()).build();
	}

	@Override
	public Page<HotelFacilityDetailResponse> findAllHotelFacility(Predicate predicate, Pageable pageable) {
		return hotelFacilityService.findAll(predicate, pageable).map(this::buildHotelFacilityDetailResponse);
	}
	
	private HotelFacilityDetailResponse buildHotelFacilityDetailResponse(final HotelFacility hotelFacility) {
		HotelFacilityDetailResponse response = new HotelFacilityDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<HotelFacility, HotelFacilityDetailResponse> typeMap = modelMapper.typeMap(HotelFacility.class, HotelFacilityDetailResponse.class);
		typeMap.map(hotelFacility, response);

		return response;
	}
	
	private HotelFacility buildHotelFacility(final HotelFacilityCreateRequest request) {
		HotelFacility hotelFacility = new HotelFacility();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<HotelFacilityCreateRequest, HotelFacility> typeMap = modelMapper.typeMap(HotelFacilityCreateRequest.class, HotelFacility.class);
		typeMap.addMappings(mapper -> mapper.skip(HotelFacility::setHotelFacilityId));
		typeMap.addMappings(mapper -> mapper.skip(HotelFacility::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(HotelFacility::setLastUpdated));
		typeMap.map(request, hotelFacility);
//        category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return hotelFacility;
	}
	
	private HotelFacility buildHotelFacility(HotelFacility hotelFacility, final HotelFacilityUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<HotelFacilityUpdateRequest, HotelFacility> typeMap = modelMapper.typeMap(HotelFacilityUpdateRequest.class, HotelFacility.class);
		typeMap.addMappings(mapper -> mapper.skip(HotelFacility::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(HotelFacility::setLastUpdated));
		typeMap.map(request, hotelFacility);
		// category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return hotelFacility;
	}


}

