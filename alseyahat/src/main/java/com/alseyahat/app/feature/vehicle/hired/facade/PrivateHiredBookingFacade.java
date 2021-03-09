package com.alseyahat.app.feature.vehicle.hired.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingCreateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingCreateResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingDetailResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingUpdateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingUpdateResponse;
import com.querydsl.core.types.Predicate;

public interface PrivateHiredBookingFacade {

	void deletePrivateHiredBooking(final String privateHiredBookingId);

	PrivateHiredBookingDetailResponse findPrivateHiredBookingId(final String privateHiredBookingId);

	PrivateHiredBookingCreateResponse createPrivateHiredBooking(final PrivateHiredBookingCreateRequest request);

	PrivateHiredBookingUpdateResponse updatePrivateHiredBooking(final PrivateHiredBookingUpdateRequest request);

	Page<PrivateHiredBookingDetailResponse> findAllPrivateHiredBooking(final Predicate predicate, final Pageable pageable);
}