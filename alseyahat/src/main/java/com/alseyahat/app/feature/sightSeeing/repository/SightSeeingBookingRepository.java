package com.alseyahat.app.feature.sightSeeing.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.sightSeeing.repository.entity.SightSeeingBooking;

public interface SightSeeingBookingRepository extends PagingAndSortingRepository<SightSeeingBooking, Long>, QuerydslPredicateExecutor<SightSeeingBooking> {

}
