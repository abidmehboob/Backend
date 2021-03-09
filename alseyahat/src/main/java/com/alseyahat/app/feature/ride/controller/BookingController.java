package com.alseyahat.app.feature.ride.controller;

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
import com.alseyahat.app.feature.ride.facade.BookingFacade;
import com.alseyahat.app.feature.ride.dto.BookingCreateRequest;
import com.alseyahat.app.feature.ride.dto.BookingCreateResponse;
import com.alseyahat.app.feature.ride.dto.BookingDetailResponse;
import com.alseyahat.app.feature.ride.dto.BookingUpdateRequest;
import com.alseyahat.app.feature.ride.dto.BookingUpdateResponse;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "Booking")
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {
	BookingFacade bookingFacade;
		
	  @PostMapping
	    @ApiOperation(value = "Create booking", nickname = "createBooking", notes = "Create booking")
	    public ResponseEntity<BookingCreateResponse> createBooking(@Valid @RequestBody final BookingCreateRequest bookingRequest) {
	        return new ResponseEntity<>(bookingFacade.createBooking(bookingRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update booking", nickname = "updateBooking", notes = "Update booking")
	    public ResponseEntity<BookingUpdateResponse> updateBooking(@Valid @RequestBody final BookingUpdateRequest bookingUpdateRequest) {
	        return ResponseEntity.ok(bookingFacade.updateBooking(bookingUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all bookings", nickname = "getAllBookings", notes = "Get all bookings")
	    public ResponseEntity<Page<BookingDetailResponse>> getAllBookings(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = Deal.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(bookingFacade.findAllBooking(predicate, pageable));
	    }

	    @GetMapping("/{bookingId}")
	    @ApiOperation(value = "Get booking by id", nickname = "getBookingById", notes = "Get booking by id")
	    public ResponseEntity<BookingDetailResponse> getBookingById(@PathVariable final String sightSeeingId) {
	        return ResponseEntity.ok(bookingFacade.findBookingId(sightSeeingId));
	    }

	    @DeleteMapping("/{bookingId}")
	    @ApiOperation(value = "Delete booking", nickname = "deleteBooking", notes = "Delete booking")
	    public ResponseEntity<Void> deleteBooking(@PathVariable final String bookingId) {
	    	bookingFacade.deleteBooking(bookingId);
	        return ResponseEntity.ok().build();
	    }

}
