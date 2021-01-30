package com.alseyahat.app.feature.hotel.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.hotel.dto.HotelCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.HotelFacade;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.querydsl.core.types.Predicate;

public class HotelFacadeImpl implements HotelFacade {

	@Override
	public void deleteHotel(String hotelId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HotelDetailResponse findHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelCreateResponse createHotel(HotelCreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelUpdateResponse updateHotel(HotelUpdateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<HotelDetailResponse> findAllHotel(Predicate predicate, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
