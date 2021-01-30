package com.alseyahat.app.feature.hotel.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alseyahat.app.feature.hotel.repository.entity.Hotel;

public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long>, QuerydslPredicateExecutor<Hotel> {

}

