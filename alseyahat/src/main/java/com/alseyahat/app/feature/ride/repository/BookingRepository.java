package com.alseyahat.app.feature.ride.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.ride.repository.entity.Booking;


public interface BookingRepository extends PagingAndSortingRepository<Booking, Long>, QuerydslPredicateExecutor<Booking> {

}