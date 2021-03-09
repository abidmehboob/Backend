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
import com.alseyahat.app.feature.deal.dto.DealBookingCreateRequest;
import com.alseyahat.app.feature.deal.dto.DealBookingCreateResponse;
import com.alseyahat.app.feature.deal.dto.DealBookingDetailResponse;
import com.alseyahat.app.feature.deal.dto.DealBookingUpdateRequest;
import com.alseyahat.app.feature.deal.dto.DealBookingUpdateResponse;
import com.alseyahat.app.feature.deal.facade.DealBookingFacade;
import com.alseyahat.app.feature.deal.repository.entity.DealBooking;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "DealBooking")
@RequestMapping("/deal/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DealBookingController {
	
	DealBookingFacade dealBookingFacade;
	
	  @PostMapping
	    @ApiOperation(value = "Create dealBooking", nickname = "createDealBooking", notes = "Create dealBooking")
	    public ResponseEntity<DealBookingCreateResponse> createDealBooking(@Valid @RequestBody final DealBookingCreateRequest dealBookingRequest) {
	        return new ResponseEntity<>(dealBookingFacade.createDealBooking(dealBookingRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update dealBooking", nickname = "updateDealBooking", notes = "Update dealBooking")
	    public ResponseEntity<DealBookingUpdateResponse> updateDealBooking(@Valid @RequestBody final DealBookingUpdateRequest dealBookingUpdateRequest) {
	        return ResponseEntity.ok(dealBookingFacade.updateDealBooking(dealBookingUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all dealBookings", nickname = "getAllDealBookings", notes = "Get all dealBookings")
	    public ResponseEntity<Page<DealBookingDetailResponse>> getAllDealBookings(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = DealBooking.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(dealBookingFacade.findAllDealBookings(predicate, pageable));
	    }

	    @GetMapping("/{dealBookingId}")
	    @ApiOperation(value = "Get dealBooking by id", nickname = "getDealBookingById", notes = "Get dealBooking by id")
	    public ResponseEntity<DealBookingDetailResponse> getDealBookingById(@PathVariable final String dealBookingId) {
	        return ResponseEntity.ok(dealBookingFacade.findDealBookingId(dealBookingId));
	    }

	    @DeleteMapping("/{dealBookingId}")
	    @ApiOperation(value = "Delete dealBooking", nickname = "deleteDealBooking", notes = "Delete dealBooking")
	    public ResponseEntity<Void> deleteDealBooking(@PathVariable final String dealBookingId) {
	    	dealBookingFacade.deleteDealBooking(dealBookingId);
	        return ResponseEntity.ok().build();
	    }

	   
}
