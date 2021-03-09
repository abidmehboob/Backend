package com.alseyahat.app.feature.sightSeeing.controller;
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
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingCreateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingCreateResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingDetailResponse;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingUpdateRequest;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingUpdateResponse;
import com.alseyahat.app.feature.sightSeeing.facade.SightSeeingFacade;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "SightSeeing")
@RequestMapping("/sightseeings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SightSeeingController {
	SightSeeingFacade sightSeeingFacade;
		
	  @PostMapping
	    @ApiOperation(value = "Create sightSeeing", nickname = "createSightSeeing", notes = "Create sightSeeing")
	    public ResponseEntity<SightSeeingCreateResponse> createSightSeeing(@Valid @RequestBody final SightSeeingCreateRequest sightSeeingRequest) {
	        return new ResponseEntity<>(sightSeeingFacade.createSightSeeing(sightSeeingRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update sightSeeing", nickname = "updateSightSeeing", notes = "Update sightSeeing")
	    public ResponseEntity<SightSeeingUpdateResponse> updateSightSeeing(@Valid @RequestBody final SightSeeingUpdateRequest sightSeeingUpdateRequest) {
	        return ResponseEntity.ok(sightSeeingFacade.updateSightSeeing(sightSeeingUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all reviews", nickname = "getAllSightSeeings", notes = "Get all reviews")
	    public ResponseEntity<Page<SightSeeingDetailResponse>> getAllSightSeeings(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = Deal.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(sightSeeingFacade.findAllSightSeeing(predicate, pageable));
	    }

	    @GetMapping("/{sightSeeingId}")
	    @ApiOperation(value = "Get review by id", nickname = "getReviewById", notes = "Get review by id")
	    public ResponseEntity<SightSeeingDetailResponse> getSightSeeingById(@PathVariable final String sightSeeingId) {
	        return ResponseEntity.ok(sightSeeingFacade.findSightSeeingId(sightSeeingId));
	    }

	    @DeleteMapping("/{sightSeeingId}")
	    @ApiOperation(value = "Delete review", nickname = "deleteReview", notes = "Delete review")
	    public ResponseEntity<Void> deleteSightSeeing(@PathVariable final String sightSeeingId) {
	    	sightSeeingFacade.deleteSightSeeing(sightSeeingId);
	        return ResponseEntity.ok().build();
	    }

}
