package com.alseyahat.app.feature.ride.dto;

import java.util.Date;

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
public class RideRequestUpdateRequest {
	
	String RideRequestId;
	String bookingId;

	String CustomerId;

	String dropOffAddress;

	double dropOffLat;

	double dropOffLong;

	Date dropOffTime;

	String mobileNumber;

	String pickUpAddress;

	double pickUpLat;

	double pickUpLong;

	Date pickUpTime;

	String rideStatus;

	String routeID;

	String selectedDay;
	
}