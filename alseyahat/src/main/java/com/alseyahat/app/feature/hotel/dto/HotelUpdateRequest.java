package com.alseyahat.app.feature.hotel.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HotelUpdateRequest {
	
	String hotelId;
    String name;
   
    String description;

    
    String email;

    
    String phone;

    
    String registerFrom;
    
    List<String> images = new ArrayList<>();

//    
//    String logo;
//
//    
//    String backgroundImage;

    
    String accountNumber;

    
    Long singleRoomCharges;
    
    
    Long doubleRoomCharges;
    
    
    String addressLine;
    
    
    String businessType;
    
    
    String city;

    String district;
    
    String town;

    
    String postcode;

    
    Double latitude;

    
    Double longitude;
    
    boolean hotSight;
	
	boolean newArrival;
	
	boolean advertised;

}
