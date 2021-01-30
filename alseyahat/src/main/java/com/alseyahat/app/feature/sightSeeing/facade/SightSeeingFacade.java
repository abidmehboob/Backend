package com.alseyahat.app.feature.sightSeeing.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingCreateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingCreateResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingDetailResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingUpdateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingUpdateResponse;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeing;
import com.querydsl.core.types.Predicate;

public interface SightSeeingFacade {

	void deleteSightSeeing(final String sightSeeingId);

	SightSeeingDetailResponse findSightSeeingId(final String sightSeeingId);

	SightSeeingCreateResponse createSightSeeing(final SightSeeingCreateRequest request);

	SightSeeingUpdateResponse updateSightSeeing(final SightSeeingUpdateRequest request);

	Page<SightSeeingDetailResponse> findAllSightSeeing(final Predicate predicate, final Pageable pageable);
}
