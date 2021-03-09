package com.alseyahat.app.feature.vehicle.hired.dto;

import java.util.Date;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PrivateHiredBookingUpdateRequest {

	String privateHiredBookingId;
	
	String bookingStatus;
	
	Date startDate;
	
	Date endDate;
	
	String pickLocation;
	
	String paymentReceipt;
	
	String transactionId;
}
