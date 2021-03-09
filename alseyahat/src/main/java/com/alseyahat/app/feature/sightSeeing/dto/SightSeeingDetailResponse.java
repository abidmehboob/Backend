package com.alseyahat.app.feature.sightSeeing.dto;

import java.util.List;

import com.alseyahat.app.feature.review.repository.entity.Review;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SightSeeingDetailResponse {

	String name;

	String description;

	String logo;

	String images;

	String addressLine;

	String sightSeeingType;

	String city;
	
	String district;

	String town;

	String postcode;

	Double latitude;

	Double longitude;
	
	Double sightAverageRating;
	
	List<Review> reviewLst;
	
	boolean hotSight;
	
	boolean newArrival;
	
	boolean advertised;
}
