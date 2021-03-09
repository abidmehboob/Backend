package com.alseyahat.app.feature.vehicle.hired.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PrivateHiredBookingCreateRequest {

	String privateHiredId;

	String bookingStatus;

	Date startDate;

	Date endDate;

	String city;

	String district;

	Double latitude;

	Double longitude;

	String pickLocation;
}
