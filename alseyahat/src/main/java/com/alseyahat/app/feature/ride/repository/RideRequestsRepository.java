package com.alseyahat.app.feature.ride.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.ride.repository.entity.RideRequests;


public interface RideRequestsRepository extends PagingAndSortingRepository<RideRequests, Long>, QuerydslPredicateExecutor<RideRequests> {

}