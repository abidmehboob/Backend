package com.alseyahat.app.feature.deal.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alseyahat.app.feature.deal.dto.DealCreateRequest;
import com.alseyahat.app.feature.deal.dto.DealCreateResponse;
import com.alseyahat.app.feature.deal.dto.DealDetailResponse;
import com.alseyahat.app.feature.deal.dto.DealUpdateRequest;
import com.alseyahat.app.feature.deal.dto.DealUpdateResponse;
import com.alseyahat.app.feature.deal.facade.DealFacade;
import com.alseyahat.app.feature.deal.repository.entity.Deal;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "Deal")
@RequestMapping("/deals")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DealController {
	
	DealFacade dealFacade;
	
	  @PostMapping
	    @ApiOperation(value = "Create deal", nickname = "createDeal", notes = "Create deal")
	    public ResponseEntity<DealCreateResponse> createDeal(@Valid @RequestBody final DealCreateRequest dealRequest) {
	        return new ResponseEntity<>(dealFacade.createDeal(dealRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update deal", nickname = "updateDeal", notes = "Update Deal")
	    public ResponseEntity<DealUpdateResponse> updateDeal(@Valid @RequestBody final DealUpdateRequest dealUpdateRequest) {
	        return ResponseEntity.ok(dealFacade.updateDeal(dealUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all deals", nickname = "getAllDeals", notes = "Get all deals")
	    public ResponseEntity<Page<DealDetailResponse>> getAllDeals(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = Deal.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(dealFacade.findAllDeal(predicate, pageable));
	    }

	    @GetMapping("/{dealId}")
	    @ApiOperation(value = "Get deal by id", nickname = "getDealById", notes = "Get deal by id")
	    public ResponseEntity<DealDetailResponse> getDealById(@PathVariable final String dealId) {
	        return ResponseEntity.ok(dealFacade.findDealId(dealId));
	    }

	    @DeleteMapping("/{dealId}")
	    @ApiOperation(value = "Delete deal", nickname = "deleteDeal", notes = "Delete deal")
	    public ResponseEntity<Void> deleteDeal(@PathVariable final String dealId) {
	    	dealFacade.deleteDeal(dealId);
	        return ResponseEntity.ok().build();
	    }

	   
}
