package com.alseyahat.app.feature.sightSeeing.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingCreateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingCreateResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingDetailResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingUpdateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingUpdateResponse;
import com.querydsl.core.types.Predicate;

public interface SightSeeingBookingFacade {

	void deleteSightSeeingBooking(final String sightSeeingBookingId);

	SightSeeingBookingDetailResponse findSightSeeingBookingId(final String sightSeeingBookingId);

	SightSeeingBookingCreateResponse createSightSeeingBooking(final SightSeeingBookingCreateRequest request);

	SightSeeingBookingUpdateResponse updateSightSeeingBooking(final SightSeeingBookingUpdateRequest request);

	Page<SightSeeingBookingDetailResponse> findAllSightSeeingBooking(final Predicate predicate, final Pageable pageable);
}