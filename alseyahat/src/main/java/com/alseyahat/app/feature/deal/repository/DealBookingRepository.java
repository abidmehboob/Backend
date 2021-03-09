package com.alseyahat.app.feature.deal.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.deal.repository.entity.DealBooking;

public interface DealBookingRepository extends PagingAndSortingRepository<DealBooking, Long>, QuerydslPredicateExecutor<DealBooking> {

}