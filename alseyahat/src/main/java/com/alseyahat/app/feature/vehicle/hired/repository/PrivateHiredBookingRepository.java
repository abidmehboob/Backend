package com.alseyahat.app.feature.vehicle.hired.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHiredBooking;

public interface PrivateHiredBookingRepository extends PagingAndSortingRepository<PrivateHiredBooking, Long>, QuerydslPredicateExecutor<PrivateHiredBooking> {

}