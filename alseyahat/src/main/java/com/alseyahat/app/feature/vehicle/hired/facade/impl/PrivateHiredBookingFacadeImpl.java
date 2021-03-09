package com.alseyahat.app.feature.vehicle.hired.facade.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingCreateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingCreateResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingDetailResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingUpdateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingUpdateResponse;
import com.alseyahat.app.feature.vehicle.hired.facade.PrivateHiredBookingFacade;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PrivateHiredBookingFacadeImpl implements PrivateHiredBookingFacade {

	@Override
	public void deletePrivateHiredBooking(String privateHiredBookingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PrivateHiredBookingDetailResponse findPrivateHiredBookingId(String privateHiredBookingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrivateHiredBookingCreateResponse createPrivateHiredBooking(PrivateHiredBookingCreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrivateHiredBookingUpdateResponse updatePrivateHiredBooking(PrivateHiredBookingUpdateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PrivateHiredBookingDetailResponse> findAllPrivateHiredBooking(Predicate predicate, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
