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
import com.alseyahat.app.feature.hotel.dto.HotelBookingCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelBookingCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelBookingDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelBookingUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelBookingUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.HotelBookingFacade;
import com.alseyahat.app.feature.hotel.repository.entity.HotelBooking;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "HotelBooking")
@RequestMapping("/hotel/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelBookingController {
	
	HotelBookingFacade hotelBookingFacade;
	
	  @PostMapping
	    @ApiOperation(value = "Create hotelBooking", nickname = "createHotelBooking", notes = "Create hotelBooking")
	    public ResponseEntity<HotelBookingCreateResponse> createHotelBooking(@Valid @RequestBody final HotelBookingCreateRequest hotelBookingRequest) {
	        return new ResponseEntity<>(hotelBookingFacade.createHotelBooking(hotelBookingRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update HotelBooking", nickname = "updateHotelBooking", notes = "Update hotelBooking")
	    public ResponseEntity<HotelBookingUpdateResponse> updateHotelBooking(@Valid @RequestBody final HotelBookingUpdateRequest hotelBookingUpdateRequest) {
	        return ResponseEntity.ok(hotelBookingFacade.updateHotelBooking(hotelBookingUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all HotelBooking", nickname = "getAllHotelBookings", notes = "Get all hotelBooking")
	    public ResponseEntity<Page<HotelBookingDetailResponse>> getAllHotelBookings(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = HotelBooking.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(hotelBookingFacade.findAllHotelBooking(predicate, pageable));
	    }

	    @GetMapping("/{hotelBookingId}")
	    @ApiOperation(value = "Get hotelBooking by id", nickname = "getHotelBookingById", notes = "Get HotelBooking by id")
	    public ResponseEntity<HotelBookingDetailResponse> getHotelBookingById(@PathVariable final String hotelBookingId) {
	        return ResponseEntity.ok(hotelBookingFacade.findHotelBookingId(hotelBookingId));
	    }

	    @DeleteMapping("/{hotelBookingId}")
	    @ApiOperation(value = "Delete HotelBooking", nickname = "deleteHotelBooking", notes = "Delete HotelBooking")
	    public ResponseEntity<Void> deleteHotelBooking(@PathVariable final String hotelBookingId) {
	    	hotelBookingFacade.deleteHotelBooking(hotelBookingId);
	        return ResponseEntity.ok().build();
	    }

	   
}

