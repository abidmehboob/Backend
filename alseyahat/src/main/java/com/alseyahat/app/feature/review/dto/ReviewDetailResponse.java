package com.alseyahat.app.feature.review.dto;

import com.alseyahat.app.feature.deal.repository.entity.Deal;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeing;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReviewDetailResponse {

	String reviewRatingId;

	Hotel hotel;

	SightSeeing sightSeeing;

	Deal deal;

	String reviewFor;

	Integer rating;

	String review;
	
	String customerId;

}
