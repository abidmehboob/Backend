package com.alseyahat.app.feature.deal.dto;

import java.util.List;

import com.alseyahat.app.feature.hotel.dto.HotelDetailResponse;
import com.alseyahat.app.feature.review.repository.entity.Review;
import com.alseyahat.app.feature.sightSeeing.dto.SightSeeingDetailResponse;
import com.alseyahat.app.feature.vehicle.hired.dto.PrivateHiredDetailResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DealDetailResponse {

	String dealId;
	
	HotelDetailResponse hotelDetailResponse;
	
	SightSeeingDetailResponse sightSeeingDetailResponse;
	
	PrivateHiredDetailResponse privateHiredDetailResponse;
	
	String name;

	String description;
	
	Double dealAverageRating;
	
	List<Review> reviewLst;
}
