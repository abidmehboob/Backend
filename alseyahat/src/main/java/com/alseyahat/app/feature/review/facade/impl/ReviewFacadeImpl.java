package com.alseyahat.app.feature.review.facade.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.review.dto.ReviewCreateRequest;
import com.alseyahat.app.feature.review.dto.ReviewCreateResponse;
import com.alseyahat.app.feature.review.dto.ReviewDetailResponse;
import com.alseyahat.app.feature.review.dto.ReviewUpdateRequest;
import com.alseyahat.app.feature.review.dto.ReviewUpdateResponse;
import com.alseyahat.app.feature.review.facade.ReviewFacade;
import com.alseyahat.app.feature.review.repository.entity.QReview;
import com.alseyahat.app.feature.review.repository.entity.Review;
import com.alseyahat.app.feature.review.service.ReviewService;
import com.querydsl.core.types.Predicate;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReviewFacadeImpl implements ReviewFacade {

	ReviewService reviewService;
	ModelMapper modelMapper;
	
	@Override
	public void deleteReview(String reviewId) {
		Review review = reviewService.findOne(QReview.review1.reviewRatingId.eq(reviewId));
		if (review.isEnabled())
			review.setEnabled(Boolean.FALSE);
		else
			review.setEnabled(Boolean.TRUE);

		reviewService.save(review);
		log.trace("Review deleted with id [{}]", reviewId);
	}

	@Override
	public ReviewDetailResponse findReviewId(String reviewId) {
		Review review = reviewService.findOne(QReview.review1.reviewRatingId.eq(reviewId));
		return buildReviewDetailResponse(review);
	}

	@Override
	public ReviewCreateResponse createReview(ReviewCreateRequest request) {
		final Review savedReview = reviewService.save(buildReview(request));
		return ReviewCreateResponse.builder().ReviewRatingId(savedReview.getReviewRatingId()).build();
	}

	@Override
	public ReviewUpdateResponse updateReview(ReviewUpdateRequest request) {
		Review review = reviewService.findOne(QReview.review1.reviewRatingId.eq(request.getReviewRatingId()));
		final Review savedReview = reviewService.save(buildReview(review, request));
		return ReviewUpdateResponse.builder().ReviewRatingId(savedReview.getReviewRatingId()).build();
	}

	@Override
	public Page<ReviewDetailResponse> findAllReview(Predicate predicate, Pageable pageable) {
		return reviewService.findAll(predicate, pageable).map(this::buildReviewDetailResponse);
	}
	
	private ReviewDetailResponse buildReviewDetailResponse(final Review review) {
		ReviewDetailResponse response = new ReviewDetailResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<Review, ReviewDetailResponse> typeMap = modelMapper.typeMap(Review.class, ReviewDetailResponse.class);
		typeMap.map(review, response);

		return response;
	}
	
	private Review buildReview(final ReviewCreateRequest request) {
		Review review = new Review();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		final TypeMap<ReviewCreateRequest, Review> typeMap = modelMapper.typeMap(ReviewCreateRequest.class, Review.class);
		typeMap.addMappings(mapper -> mapper.skip(Review::setReviewRatingId));
		typeMap.addMappings(mapper -> mapper.skip(Review::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Review::setLastUpdated));
		typeMap.map(request, review);
//        category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return review;
	}
	
	private Review buildReview(Review review, final ReviewUpdateRequest request) {
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<ReviewUpdateRequest, Review> typeMap = modelMapper.typeMap(ReviewUpdateRequest.class, Review.class);
		typeMap.addMappings(mapper -> mapper.skip(Review::setDateCreated));
		typeMap.addMappings(mapper -> mapper.skip(Review::setLastUpdated));
		typeMap.map(request, review);
		// category.setImages(request.getImages().stream().map(Object::toString).collect(Collectors.joining(SEPARATOR)));

		return review;
	}


}
