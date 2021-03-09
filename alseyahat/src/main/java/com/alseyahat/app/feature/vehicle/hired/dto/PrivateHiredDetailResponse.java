package com.alseyahat.app.feature.vehicle.hired.dto;

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
public class PrivateHiredDetailResponse {

	String privateHiredId;
	String name;
	String description;
	String type;
	String numberOfSeat;
	boolean driverRequired;
	boolean fuel;
	double perDayRate;
	String city;
	String district;
	Double latitude;
	Double longitude;
	Double privateHiredAverageRating;
	List<Review> reviewLst;
	String images;
}
