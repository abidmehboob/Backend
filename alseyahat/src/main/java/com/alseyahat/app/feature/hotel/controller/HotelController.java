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
import com.alseyahat.app.feature.hotel.dto.HotelCreateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelCreateResponse;
import com.alseyahat.app.feature.hotel.dto.HotelDetailResponse;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateRequest;
import com.alseyahat.app.feature.hotel.dto.HotelUpdateResponse;
import com.alseyahat.app.feature.hotel.facade.HotelFacade;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "Hotel")
@RequestMapping("/hotels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {
	
	  HotelFacade hotelFacade;
	
	  @PostMapping
	    @ApiOperation(value = "Create hotel", nickname = "createHotel", notes = "Create hotel")
	    public ResponseEntity<HotelCreateResponse> createHotel(@Valid @RequestBody final HotelCreateRequest hotelRequest) {
	        return new ResponseEntity<>(hotelFacade.createHotel(hotelRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update hotel", nickname = "updateHotel", notes = "Update hotel")
	    public ResponseEntity<HotelUpdateResponse> updateHotel(@Valid @RequestBody final HotelUpdateRequest hotelUpdateRequest) {
	        return ResponseEntity.ok(hotelFacade.updateHotel(hotelUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all hotels", nickname = "getAllHotels", notes = "Get all hotels")
	    public ResponseEntity<Page<HotelDetailResponse>> getAllHotels(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = Hotel.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(hotelFacade.findAllHotel(predicate, pageable));
	    }

	    @GetMapping("/{hotelId}")
	    @ApiOperation(value = "Get hotel by id", nickname = "getHotelById", notes = "Get hotel by id")
	    public ResponseEntity<HotelDetailResponse> getHotelById(@PathVariable final String hotelId) {
	        return ResponseEntity.ok(hotelFacade.findHotelId(hotelId));
	    }

	    @DeleteMapping("/{hotelId}")
	    @ApiOperation(value = "Delete hotel", nickname = "deleteHotel", notes = "Delete hotel")
	    public ResponseEntity<Void> deleteHotel(@PathVariable final String hotelId) {
	    	hotelFacade.deleteHotel(hotelId);
	        return ResponseEntity.ok().build();
	    }

	   
}
