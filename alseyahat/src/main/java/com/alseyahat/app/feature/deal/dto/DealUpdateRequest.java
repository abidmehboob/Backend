package com.alseyahat.app.feature.deal.dto;

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
public class DealUpdateRequest {

	String dealId;
	
	String name;

	String description;
	
	List<String> images = new ArrayList<>();
	
	boolean hotSight;
	
	boolean newArrival;
	
	boolean advertised;
}
