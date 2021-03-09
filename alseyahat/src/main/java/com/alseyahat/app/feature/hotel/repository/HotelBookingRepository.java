package com.alseyahat.app.feature.hotel.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alseyahat.app.feature.hotel.repository.entity.HotelBooking;

public interface HotelBookingRepository extends PagingAndSortingRepository<HotelBooking, Long>, QuerydslPredicateExecutor<HotelBooking> {

}

