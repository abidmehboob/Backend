package com.alseyahat.app.feature.sightSeeing.dto;

import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeing;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SightSeeingBookingDetailResponse {

	String sightSeeingBookingId;
	
    SightSeeing sightSeeing;
    
    Customer customer;
	
    String bookedDate;
	
	String pickLocation;
	
	String paymentReceipt;
	
	String transactionId;
}
