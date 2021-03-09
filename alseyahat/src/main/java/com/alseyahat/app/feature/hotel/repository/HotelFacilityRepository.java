package com.alseyahat.app.feature.hotel.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alseyahat.app.feature.hotel.repository.entity.HotelFacility;

public interface HotelFacilityRepository extends PagingAndSortingRepository<HotelFacility, Long>, QuerydslPredicateExecutor<HotelFacility> {

}

