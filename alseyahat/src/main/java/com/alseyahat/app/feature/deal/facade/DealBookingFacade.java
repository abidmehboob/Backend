package com.alseyahat.app.feature.deal.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.deal.dto.DealBookingCreateRequest;
import com.alseyahat.app.feature.deal.dto.DealBookingCreateResponse;
import com.alseyahat.app.feature.deal.dto.DealBookingDetailResponse;
import com.alseyahat.app.feature.deal.dto.DealBookingUpdateRequest;
import com.alseyahat.app.feature.deal.dto.DealBookingUpdateResponse;
import com.querydsl.core.types.Predicate;

public interface DealBookingFacade {
	void deleteDealBooking(final String dealId);

	DealBookingDetailResponse findDealBookingId(final String dealBookingId);

	DealBookingCreateResponse createDealBooking(final DealBookingCreateRequest request);

	DealBookingUpdateResponse updateDealBooking(final DealBookingUpdateRequest request);

	Page<DealBookingDetailResponse> findAllDealBookings(final Predicate predicate, final Pageable pageable);

}
