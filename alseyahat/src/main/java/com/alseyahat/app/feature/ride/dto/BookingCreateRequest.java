package com.alseyahat.app.feature.ride.dto;

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
public class BookingCreateRequest {

	
   String dropOffAddress;

	double dropOffLat;

	double dropOffLong;

	String dropOffTime;

	String pickUpAddress;

	double pickUpLat;

	double pickUpLong;

	String pickUpTime;

	String rideStatus;

	String routeID;

	String selectedDay;

}