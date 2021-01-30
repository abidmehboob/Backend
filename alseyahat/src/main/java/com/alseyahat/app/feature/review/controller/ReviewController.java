package com.alseyahat.app.feature.review.controller;
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
import com.alseyahat.app.feature.review.dto.ReviewCreateRequest;
import com.alseyahat.app.feature.review.dto.ReviewCreateResponse;
import com.alseyahat.app.feature.review.dto.ReviewDetailResponse;
import com.alseyahat.app.feature.review.dto.ReviewUpdateRequest;
import com.alseyahat.app.feature.review.dto.ReviewUpdateResponse;
import com.alseyahat.app.feature.review.facade.ReviewFacade;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "Review")
@RequestMapping("/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {
	 ReviewFacade reviewFacade;
		
	  @PostMapping
	    @ApiOperation(value = "Create review", nickname = "createReview", notes = "Create review")
	    public ResponseEntity<ReviewCreateResponse> createReview(@Valid @RequestBody final ReviewCreateRequest reviewRequest) {
	        return new ResponseEntity<>(reviewFacade.createReview(reviewRequest), HttpStatus.CREATED);
	    }

	    @PatchMapping
	    @ApiOperation(value = "Update review", nickname = "updateReview", notes = "Update review")
	    public ResponseEntity<ReviewUpdateResponse> updateReview(@Valid @RequestBody final ReviewUpdateRequest reviewUpdateRequest) {
	        return ResponseEntity.ok(reviewFacade.updateReview(reviewUpdateRequest));
	    }

	    @GetMapping
	    @ApiOperation(value = "Get all reviews", nickname = "getAllReviews", notes = "Get all reviews")
	    public ResponseEntity<Page<ReviewDetailResponse>> getAllReviews(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = Deal.class) Predicate predicate, final Pageable pageable) {
	         return ResponseEntity.ok(reviewFacade.findAllReview(predicate, pageable));
	    }

	    @GetMapping("/{reviewId}")
	    @ApiOperation(value = "Get review by id", nickname = "getReviewById", notes = "Get review by id")
	    public ResponseEntity<ReviewDetailResponse> getReviewById(@PathVariable final String reviewId) {
	        return ResponseEntity.ok(reviewFacade.findReviewId(reviewId));
	    }

	    @DeleteMapping("/{reviewId}")
	    @ApiOperation(value = "Delete review", nickname = "deleteReview", notes = "Delete review")
	    public ResponseEntity<Void> deleteReview(@PathVariable final String reviewId) {
	    	reviewFacade.deleteReview(reviewId);
	        return ResponseEntity.ok().build();
	    }

	   
}
