package com.alseyahat.app.feature.hotel.dto;

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
public class HotelCreateRequest {

	String name;

    
    String description;

    
    String email;

    
    String phone;

    
    String registerFrom;

//    String images;
    List<String> images = new ArrayList<>();
    
    String accountNumber;

    
    Long singleRoomCharges;
    
    
    Long doubleRoomCharges;
    
    
    String addressLine;
    
    
    String businessType;
    
    
    String city;

    
    String town;

    
    String postcode;

    
    Double latitude;

    
    Double longitude;
    
}
