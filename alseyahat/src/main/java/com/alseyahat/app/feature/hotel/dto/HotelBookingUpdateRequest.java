package com.alseyahat.app.feature.hotel.dto;

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
public class HotelBookingUpdateRequest {

    String hotelBookingId;
	
   	String bookingStatus;
   	
   	String roomType;
   	
   	Integer childern;
   	
	Integer adult;
	
	Integer extraMatress;

	 String pickLocation;

	String sartDate;
	
	String endDate;
	
	String paymentReceipt;

	String transactionId;
}
