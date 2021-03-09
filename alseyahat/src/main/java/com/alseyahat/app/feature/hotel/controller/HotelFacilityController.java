package com.alseyahat.app.feature.hotel.controller;

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
import com.alseyahat.app.feature.deal.repository.entity.Deal;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelFacilityUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.HotelFacilityFacade;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "HotelFacility")
@RequestMapping("/hotelfacility")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelFacilityController {
	HotelFacilityFacade hotelFacilityFacade;
	
	  @PostMapping
	    @ApiOperation(value = "Create hotelFacility", nickname = "createHotelFacility", notes = "Create HotelFacility")
	    public ResponseEntity<HotelFacilityCreateResponse> createHotelFacility(@Valid @RequestBody final HotelFacilityCreateRequest hotelFacilityRequest) {
	        return new ResponseEntity<>(hotelFacilityFacade.createHotelFacility(hotelFacilityRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update hotelFacility", nickname = "hotelFacility", notes = "Update hotelFacility")
	    public ResponseEntity<HotelFacilityUpdateResponse> updateHotelFacility(@Valid @RequestBody final HotelFacilityUpdateRequest hotelFacilityUpdateRequest) {
	        return ResponseEntity.ok(hotelFacilityFacade.updateHotelFacility(hotelFacilityUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all hotelFacilities", nickname = "getAllHotelFacilities", notes = "Get all hotelFacilities")
	    public ResponseEntity<Page<HotelFacilityDetailResponse>> getAllHotelFacilities(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = Deal.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(hotelFacilityFacade.findAllHotelFacility(predicate, pageable));
	    }

	    @GetMapping("/{hotelFacilityId}")
	    @ApiOperation(value = "Get hotel by id", nickname = "getHotelById", notes = "Get hotel by id")
	    public ResponseEntity<HotelFacilityDetailResponse> getHotelFacilityById(@PathVariable final String hotelFacilityId) {
	        return ResponseEntity.ok(hotelFacilityFacade.findHotelFacilityId(hotelFacilityId));
	    }

	    @DeleteMapping("/{hotelFacilityId}")
	    @ApiOperation(value = "Delete HotelFacility", nickname = "deleteHotelFacility", notes = "Delete hotelFacility")
	    public ResponseEntity<Void> deleteHotelFacility(@PathVariable final String hotelFacilityId) {
	    	hotelFacilityFacade.deleteHotelFacility(hotelFacilityId);
	        return ResponseEntity.ok().build();
	    }

	   
}

