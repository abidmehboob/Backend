package com.alseyahat.app.feature.ride.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.ride.dto.BookingDetailResponse;
import com.alseyahat.app.feature.ride.dto.RideRequestCreateRequest;
import com.alseyahat.app.feature.ride.dto.RideRequestCreateResponse;
import com.alseyahat.app.feature.ride.dto.RideRequestDetailResponse;
import com.alseyahat.app.feature.ride.dto.RideRequestUpdateRequest;
import com.alseyahat.app.feature.ride.dto.RideRequestUpdateResponse;
import com.querydsl.core.types.Predicate;

public interface RideRequestsFacade {
	
	void deleteRideRequest(final String rideRequestId);

	RideRequestDetailResponse findRideRequestId(final String rideRequestId);

	RideRequestCreateResponse createRideRequest(final RideRequestCreateRequest request);

	RideRequestUpdateResponse updateRideRequest(final RideRequestUpdateRequest request);

	Page<RideRequestDetailResponse> findAllRideRequest(final Predicate predicate, final Pageable pageable);
}