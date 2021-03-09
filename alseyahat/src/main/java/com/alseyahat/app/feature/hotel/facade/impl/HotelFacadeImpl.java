package com.alseyahat.app.feature.hotel.facade.impl;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.hotel.dto.HotelCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.HotelFacade;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.alseyahat.app.feature.hotel.repository.entity.QHotel;
import com.alseyahat.app.feature.hotel.repository.entity.QHotelFacility;
import com.alseyahat.app.feature.hotel.service.HotelFacilityService;
import com.alseyahat.app.feature.hotel.service.HotelService;
import com.alseyahat.app.feature.review.repository.entity.QReview;
import com.alseyahat.app.feature.review.service.ReviewService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;
import static com.alseyahat.app.commons.Constants.SEPARATOR;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelFacadeImpl implements HotelFacade {
	
	HotelService hotelService;
	
    ModelMapper modelMapper;
    
    HotelFacilityService hotelFacilityService;
    
    ReviewService reviewService;
	
	@Override
	public void deleteHotel(String hotelId) {
		Hotel hotel = hotelService.findOne(QHotel.hotel.hotelId.eq(hotelId));
		if (hotel.isEnabled())
			hotel.setEnabled(Boolean.FALSE);
		else
			hotel.setEnabled(Boolean.TRUE);

		hotelService.save(hotel);
		log.trace("Review deleted with id [{}]", hotelId);
	}

	@Override
	public HotelDetailResponse findHotelId(String hotelId) {
		Hotel hotel = hotelService.findOne(QHotel.hotel.hotelId.eq(hotelId).and(QHotel.hotel.isEnabled.isTrue()));
		return buildHotelDetailResponse(hotel);
	}

	@Override
	public HotelCreateResponse createHotel(HotelCreateRequest request) {
		final Hotel savedHotel = hotelService.save(buildHotel(request));
		return HotelCreateResponse.builder().hotelId(savedHotel.getHotelId()).build();
	}

	@Override
	public HotelUpdateResponse updateHotel(HotelUpdateRequest request) {
		Hotel hotel  =hotelService.findOne(QHotel.hotel.hotelId.eq(request.getHotelId()));
		final Hotel savedHotel = hotelService.save(buildHotel(hotel, request));
		return HotelUpdateResponse.builder().hotelId(savedHotel.getHotelId()).build();
	}

	@Override
	public Page<HotelDetailResponse> findAllHotel(Predicate predicate, Pageable pageable) {
		return hotelService.findAll(ExpressionUtils.allOf(predicate,QHotel.hotel.isEnabled.isTrue()), pageable).map(this::buildHotelDetailResponse);
	}
	
	private HotelDetailResponse buildHotelDetailResponse(final Hotel hotel) {
		HotelDetailResponse response = new HotelDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<Hotel, HotelDetailResponse> typeMap = modelMapper.typeMap(Hotel.class, HotelDetailResponse.class);
		typeMap.map(hotel, response);
		response.setHotelFacilityLst(hotelFacilityService.findAll(QHotelFacility.hotelFacility.hotel.eq(hotel), Pageable.unpaged()).getContent());
		response.setReviewLst(reviewService.findAll(QReview.review1.hotel.eq(hotel), Pageable.unpaged()).getContent());
		return response;
	}
	
	private Hotel buildHotel(final HotelCreateRequest request) {
		Hotel hotel = new Hotel();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<HotelCreateRequest, Hotel> typeMap = modelMapper.typeMap(HotelCreateRequest.class, Hotel.class);
		typeMap.addMappings(mapper -> mapper.skip(Hotel::setHotelId));
		typeMap.addMappings(mapper -> mapper.skip(Hotel::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Hotel::setLastUpdated));
		typeMap.map(request, hotel);
		hotel.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return hotel;
	}
	
	private Hotel buildHotel(Hotel hotel, final HotelUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<HotelUpdateRequest, Hotel> typeMap = modelMapper.typeMap(HotelUpdateRequest.class, Hotel.class);
		typeMap.addMappings(mapper -> mapper.skip(Hotel::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Hotel::setLastUpdated));
		typeMap.map(request, hotel);
		hotel.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return hotel;
	}


}

