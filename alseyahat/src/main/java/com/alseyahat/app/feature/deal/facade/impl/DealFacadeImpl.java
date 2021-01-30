package com.alseyahat.app.feature.deal.facade.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alseyahat.app.feature.deal.dto.DealCreateRequest;
import com.alseyahat.app.feature.deal.dto.DealCreateResponse;
import com.alseyahat.app.feature.deal.dto.DealDetailResponse;
import com.alseyahat.app.feature.deal.dto.DealUpdateRequest;
import com.alseyahat.app.feature.deal.dto.DealUpdateResponse;
import com.alseyahat.app.feature.deal.facade.DealFacade;
import com.alseyahat.app.feature.deal.repository.entity.Deal;
import com.alseyahat.app.feature.deal.repository.entity.QDeal;
import com.alseyahat.app.feature.deal.service.DealService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DealFacadeImpl implements DealFacade {

	DealService dealService;
	ModelMapper modelMapper;

	@Override
	public void deleteDeal(String dealId) {

		Deal deal = dealService.findOne(QDeal.deal.dealId.eq(dealId));
		if (deal.isEnabled())
			deal.setEnabled(Boolean.FALSE);
		else
			deal.setEnabled(Boolean.TRUE);

		dealService.save(deal);
		log.trace("Deal deleted with id [{}]", dealId);
	}

	@Override
	public DealDetailResponse findDealId(String dealId) {
		Deal deal = dealService.findOne(QDeal.deal.dealId.eq(dealId));
		return buildDealDetailResponse(deal);
	}

	@Override
	public DealCreateResponse createDeal(DealCreateRequest request) {
		final Deal savedDeal = dealService.save(buildDeal(request));
		return DealCreateResponse.builder().dealId(savedDeal.getDealId()).build();
	}

	@Override
	public DealUpdateResponse updateDeal(DealUpdateRequest request) {
		Deal deal = dealService.findOne(QDeal.deal.dealId.eq(request.getDealId()));
		final Deal updatedDeal = dealService.save(buildDeal(deal, request));
		return DealUpdateResponse.builder().dealId(updatedDeal.getDealId()).build();
	}

	@Override
	public Page<DealDetailResponse> findAllDeal(Predicate predicate, Pageable pageable) {
		return dealService.findAll(predicate, pageable).map(this::buildDealDetailResponse);
	}

	private DealDetailResponse buildDealDetailResponse(final Deal deal) {
		DealDetailResponse response = new DealDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<Deal, DealDetailResponse> typeMap = modelMapper.typeMap(Deal.class, DealDetailResponse.class);
		typeMap.map(deal, response);

		return response;
	}

	private Deal buildDeal(final DealCreateRequest request) {
		Deal deal = new Deal();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<DealCreateRequest, Deal> typeMap = modelMapper.typeMap(DealCreateRequest.class, Deal.class);
		typeMap.addMappings(mapper -> mapper.skip(Deal::setDealId));
		typeMap.addMappings(mapper -> mapper.skip(Deal::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Deal::setLastUpdated));
		typeMap.map(request, deal);
//        category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return deal;
	}

	private Deal buildDeal(Deal deal, final DealUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<DealUpdateRequest, Deal> typeMap = modelMapper.typeMap(DealUpdateRequest.class, Deal.class);
		typeMap.addMappings(mapper -> mapper.skip(Deal::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Deal::setLastUpdated));
		typeMap.map(request, deal);
		// category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return deal;
	}

}
