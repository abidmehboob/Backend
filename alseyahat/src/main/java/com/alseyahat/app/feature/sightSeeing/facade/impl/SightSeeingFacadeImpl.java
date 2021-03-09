package com.alseyahat.app.feature.sightSeeing.facade.impl;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.review.repository.entity.QReview;
import com.alseyahat.app.feature.review.service.ReviewService;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingCreateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingCreateResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingDetailResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingUpdateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingUpdateResponse;
import com.alseyahat.app.feature.sightSeeing.facade.SightSeeingFacade;
import com.alseyahat.app.feature.sightSeeing.repository.entity.QSightSeeing;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeing;
import com.alseyahat.app.feature.sightSeeing.service.SightSeeingService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;
import static com.alseyahat.app.commons.Constants.SEPARATOR;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SightSeeingFacadeImpl implements SightSeeingFacade {

	SightSeeingService sightSeeingService;
	ModelMapper modelMapper;
	ReviewService reviewService;
	
	@Override
	public void deleteSightSeeing(String sightSeeingId) {
		SightSeeing sightSeeing = sightSeeingService.findOne(QSightSeeing.sightSeeing.sightSeeingId.eq(sightSeeingId));
		if (sightSeeing.isEnabled())
			sightSeeing.setEnabled(Boolean.FALSE);
		else
			sightSeeing.setEnabled(Boolean.TRUE);

		sightSeeingService.save(sightSeeing);
		log.trace("Review deleted with id [{}]", sightSeeingId);
	}

	@Override
	public SightSeeingDetailResponse findSightSeeingId(String sightSeeingId) {
		SightSeeing sightSeeing = sightSeeingService.findOne(QSightSeeing.sightSeeing.sightSeeingId.eq(sightSeeingId).and(QSightSeeing.sightSeeing.isEnabled.isTrue()));
		return buildSightSeeingDetailResponse(sightSeeing);
	}

	@Override
	public SightSeeingCreateResponse createSightSeeing(SightSeeingCreateRequest request) {
		final SightSeeing savedSightSeeing = sightSeeingService.save(buildSightSeeing(request));
		return SightSeeingCreateResponse.builder().SightSeeingId(savedSightSeeing.getSightSeeingId()).build();
	}

	@Override
	public SightSeeingUpdateResponse updateSightSeeing(SightSeeingUpdateRequest request) {
		SightSeeing sightSeeing = sightSeeingService.findOne(QSightSeeing.sightSeeing.sightSeeingId.eq(request.getSightSeeingId()));
		final SightSeeing savedSightSeeing = sightSeeingService.save(buildSightSeeing(sightSeeing, request));
		return SightSeeingUpdateResponse.builder().SightSeeingId(savedSightSeeing.getSightSeeingId()).build();
	}

	@Override
	public Page<SightSeeingDetailResponse> findAllSightSeeing(Predicate predicate, Pageable pageable) {
		return sightSeeingService.findAll(ExpressionUtils.allOf(predicate,QSightSeeing.sightSeeing.isEnabled.isTrue()), pageable).map(this::buildSightSeeingDetailResponse);
	}
	
	private SightSeeingDetailResponse buildSightSeeingDetailResponse(final SightSeeing sightSeeing) {
		SightSeeingDetailResponse response = new SightSeeingDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<SightSeeing, SightSeeingDetailResponse> typeMap = modelMapper.typeMap(SightSeeing.class, SightSeeingDetailResponse.class);
		typeMap.map(sightSeeing, response);
		response.setReviewLst(reviewService.findAll(QReview.review1.sightSeeing.eq(sightSeeing), Pageable.unpaged()).getContent());
		return response;
	}
	
	private SightSeeing buildSightSeeing(final SightSeeingCreateRequest request) {
		SightSeeing sightSeeing = new SightSeeing();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<SightSeeingCreateRequest, SightSeeing> typeMap = modelMapper.typeMap(SightSeeingCreateRequest.class, SightSeeing.class);
		typeMap.addMappings(mapper -> mapper.skip(SightSeeing::setSightSeeingId));
		typeMap.addMappings(mapper -> mapper.skip(SightSeeing::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(SightSeeing::setLastUpdated));
		typeMap.map(request, sightSeeing);
		sightSeeing.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return sightSeeing;
	}
	
	private SightSeeing buildSightSeeing(SightSeeing sightSeeing, final SightSeeingUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<SightSeeingUpdateRequest, SightSeeing> typeMap = modelMapper.typeMap(SightSeeingUpdateRequest.class, SightSeeing.class);
		typeMap.addMappings(mapper -> mapper.skip(SightSeeing::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(SightSeeing::setLastUpdated));
		typeMap.map(request, sightSeeing);
		sightSeeing.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return sightSeeing;
	}


}
