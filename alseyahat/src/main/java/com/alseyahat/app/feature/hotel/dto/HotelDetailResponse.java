package com.alseyahat.app.feature.hotel.dto;

import com.alseyahat.app.feature.hotel.repository.entity.HotelFacility;
import com.alseyahat.app.feature.review.repository.entity.Review;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HotelDetailResponse {

	String hotelId;
	
	String name;

	String description;

	String email;

	String phone;

	String registerFrom;

//	String logo;
//
//	String backgroundImage;

	String images;
	
	String accountNumber;

	Long singleRoomCharges;

	Long doubleRoomCharges;

	String addressLine;

	String businessType;

	String city;

	String district;
	
	String town;

	String postcode;

	Double latitude;

	Double longitude;

	Double hotelAverageRating;
	
	List<Review> reviewLst;

	List<HotelFacility> hotelFacilityLst;

}
