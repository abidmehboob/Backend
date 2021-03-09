package com.alseyahat.app.feature.hotel.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.hotel.dto.RoomTypeCreateRequest;
import com.alseyahat.app.feature.hotel.dto.RoomTypeCreateResponse;
import com.alseyahat.app.feature.hotel.dto.RoomTypeDetailResponse;
import com.alseyahat.app.feature.hotel.dto.RoomTypeUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.RoomTypeUpdateResponse;
import com.querydsl.core.types.Predicate;

public interface RoomTypeFacade {

	void deleteRoomType(final String roomTypeId);

	RoomTypeDetailResponse findRoomTypeId(final String roomTypeId);

	RoomTypeCreateResponse createRoomType(final RoomTypeCreateRequest request);

	RoomTypeUpdateResponse updateRoomType(final RoomTypeUpdateRequest request);

	Page<RoomTypeDetailResponse> findAllRoomType(final Predicate predicate, final Pageable pageable);
}