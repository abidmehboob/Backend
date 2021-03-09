package com.alseyahat.app.feature.hotel.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.hotel.dto.HotelFacilityCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityUpdateResponse;
import com.querydsl.core.types.Predicate;

public interface HotelFacilityFacade {
	
	void deleteHotelFacility(final String hotelFacilityId);

	HotelFacilityDetailResponse findHotelFacilityId(final String hotelFacilityId);

	HotelFacilityCreateResponse createHotelFacility(final HotelFacilityCreateRequest request);

	HotelFacilityUpdateResponse updateHotelFacility(final HotelFacilityUpdateRequest request);

	Page<HotelFacilityDetailResponse> findAllHotelFacility(final Predicate predicate, final Pageable pageable);
}
