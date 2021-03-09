package com.alseyahat.app.feature.vehicle.hired.dto;

import java.util.Date;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHired;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PrivateHiredBookingDetailResponse {

    String privateHiredBookingId;
	
	String bookingStatus;
	
	Customer customer;
	
	PrivateHired privateHired;
	 
	Date startDate;
	
	Date endDate;
	
	String pickLocation;
	
	String paymentReceipt;
	
	String transactionId;
}
