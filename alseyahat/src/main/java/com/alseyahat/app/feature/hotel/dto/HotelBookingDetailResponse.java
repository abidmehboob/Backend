package com.alseyahat.app.feature.hotel.dto;

import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HotelBookingDetailResponse {

	Hotel hotel;
	
	Customer customer;

	String bookingStatus;

	String routeID;

	String sartDate;
	
	String endDate;
	
	String paymentReceipt;
	
	String transactionId;
}
