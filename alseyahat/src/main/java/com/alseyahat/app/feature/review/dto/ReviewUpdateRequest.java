package com.alseyahat.app.feature.review.dto;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReviewUpdateRequest {
	
	String reviewRatingId;

	String hotelId;

	String sightSeeingId;

	String dealId;

	String reviewFor;

	Integer rating;

	String review;

	String customerId;
}
