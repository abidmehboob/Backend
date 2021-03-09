package com.alseyahat.app.feature.sightSeeing.dto;
import java.util.ArrayList;
import java.util.List;

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
public class SightSeeingUpdateRequest {
	
	String SightSeeingId;
	
	String name;

	String description;

	List<String> images = new ArrayList<>();

	String addressLine;

	String sightSeeingType;

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
