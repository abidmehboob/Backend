package com.alseyahat.app.feature.hotel.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.hotel.dto.HotelCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateResponse;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.querydsl.core.types.Predicate;

public interface HotelFacade {

	void deleteHotel(final String hotelId);

	HotelDetailResponse findHotelId(final String hotelId);

	HotelCreateResponse createHotel(final HotelCreateRequest request);

	HotelUpdateResponse updateHotel(final HotelUpdateRequest request);

	Page<HotelDetailResponse> findAllHotel(final Predicate predicate, final Pageable pageable);
}