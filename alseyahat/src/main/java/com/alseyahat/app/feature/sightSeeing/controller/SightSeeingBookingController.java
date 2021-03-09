package com.alseyahat.app.feature.sightSeeing.controller;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingCreateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingCreateResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingDetailResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingUpdateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingBookingUpdateResponse;
import com.alseyahat.app.feature.sightSeeing.facade.SightSeeingBookingFacade;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeingBooking;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "SightSeeingBooking")
@RequestMapping("/sightseeings/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SightSeeingBookingController {
	SightSeeingBookingFacade sightSeeingBookingFacade;
		
	  @PostMapping
	    @ApiOperation(value = "Create sightSeeingBooking", nickname = "createSightSeeingBooking", notes = "Create sightSeeingBooking")
	    public ResponseEntity<SightSeeingBookingCreateResponse> createSightSeeingBooking(@Valid @RequestBody final SightSeeingBookingCreateRequest sightSeeingBookingRequest) {
	        return new ResponseEntity<>(sightSeeingBookingFacade.createSightSeeingBooking(sightSeeingBookingRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update sightSeeing", nickname = "updateSightSeeingBooking", notes = "Update sightSeeing booking")
	    public ResponseEntity<SightSeeingBookingUpdateResponse> updateSightSeeingBooking(@Valid @RequestBody final SightSeeingBookingUpdateRequest sightSeeingUpdateRequest) {
	        return ResponseEntity.ok(sightSeeingBookingFacade.updateSightSeeingBooking(sightSeeingUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all sightSeeing Booking", nickname = "getAllSightSeeingsBooking", notes = "Get all sightSeeing Booking")
	    public ResponseEntity<Page<SightSeeingBookingDetailResponse>> getAllSightSeeingsBookings(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = SightSeeingBooking.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(sightSeeingBookingFacade.findAllSightSeeingBooking(predicate, pageable));
	    }

	    @GetMapping("/{sightSeeingBookingId}")
	    @ApiOperation(value = "Get sightSeeingBooking by id", nickname = "getsightSeeingBookingId", notes = "Get sightSeeingBooking by id")
	    public ResponseEntity<SightSeeingBookingDetailResponse> getSightSeeingBookingById(@PathVariable final String sightSeeingBookingId) {
	        return ResponseEntity.ok(sightSeeingBookingFacade.findSightSeeingBookingId(sightSeeingBookingId));
	    }

//	    @DeleteMapping("/{sightSeeingBookingId}")
//	    @ApiOperation(value = "Delete sightSeeingBooking", nickname = "deletesightSeeingBooking", notes = "Delete sightSeeingBooking")
//	    public ResponseEntity<Void> deleteSightSeeingBooking(@PathVariable final String sightSeeingBookingId) {
//	    	sightSeeingBookingFacade.deleteSightSeeingBooking(sightSeeingBookingId);
//	        return ResponseEntity.ok().build();
//	    }

}
