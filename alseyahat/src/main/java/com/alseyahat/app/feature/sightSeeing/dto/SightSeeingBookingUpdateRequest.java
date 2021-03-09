package com.alseyahat.app.feature.sightSeeing.dto;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SightSeeingBookingUpdateRequest {
	
    String sightSeeingBookingId;
    
	String bookedDate;
	
	String pickLocation;
	
	String paymentReceipt;
	
	String transactionId;
}
