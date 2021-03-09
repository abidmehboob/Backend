package com.alseyahat.app.feature.deal.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DealCreateRequest {

	String hotelId;
	
	String sightSeeingId;
	
	String privateHiredId;
	 
	String name;

	String description;
	
	List<String> images = new ArrayList<>();
    
    String dealAmount;
    
    boolean hotSight;
	
	boolean newArrival;
	
	boolean advertised;

}
