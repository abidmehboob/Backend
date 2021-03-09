package com.alseyahat.app.feature.vehicle.hired.controller;

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
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingCreateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingCreateResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingDetailResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingUpdateRequest;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredBookingUpdateResponse;
import com.alseyahat.app.feature.vehicle.hired.facade.PrivateHiredBookingFacade;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHiredBooking;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "PrivateveVehicleBooking")
@RequestMapping("/privatevehicles/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrivateHiredBookingController {
	PrivateHiredBookingFacade privateHiredBookingFacade;
		
	  @PostMapping
	    @ApiOperation(value = "Create privateHired booking", nickname = "createPrivateHiredBooking", notes = "Create privateHired booking")
	    public ResponseEntity<PrivateHiredBookingCreateResponse> createPrivateHiredBooking(@Valid @RequestBody final PrivateHiredBookingCreateRequest privateHiredBookingRequest) {
	        return new ResponseEntity<>(privateHiredBookingFacade.createPrivateHiredBooking(privateHiredBookingRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update privateHired booking", nickname = "updatePrivateHiredBooking", notes = "Update privateHired booking")
	    public ResponseEntity<PrivateHiredBookingUpdateResponse> updatePrivateHiredBooking(@Valid @RequestBody final PrivateHiredBookingUpdateRequest privateHiredBookingUpdateRequest) {
	        return ResponseEntity.ok(privateHiredBookingFacade.updatePrivateHiredBooking(privateHiredBookingUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all privateHiredBooking", nickname = "getAllPrivateHiredBooking", notes = "Get all privateHired Booking")
	    public ResponseEntity<Page<PrivateHiredBookingDetailResponse>> getAllPrivateHiredBookings(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = PrivateHiredBooking.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(privateHiredBookingFacade.findAllPrivateHiredBooking(predicate, pageable));
	    }

	    @GetMapping("/{privateHiredBookingId}")
	    @ApiOperation(value = "Get private hired booking by id", nickname = "getPrivateHiredBookingById", notes = "Get privateHired booking by id")
	    public ResponseEntity<PrivateHiredBookingDetailResponse> getPrivateHiredBookingById(@PathVariable final String privateHiredBookingId) {
	        return ResponseEntity.ok(privateHiredBookingFacade.findPrivateHiredBookingId(privateHiredBookingId));
	    }

	    @DeleteMapping("/{privateHiredBookingId}")
	    @ApiOperation(value = "Delete privateHiredBooking", nickname = "deleteprivateHiredBooking", notes = "Delete privateHiredBooking")
	    public ResponseEntity<Void> deletePrivateHiredBooking(@PathVariable final String privateHiredBookingId) {
	    	privateHiredBookingFacade.deletePrivateHiredBooking(privateHiredBookingId);
	        return ResponseEntity.ok().build();
	    }

}


