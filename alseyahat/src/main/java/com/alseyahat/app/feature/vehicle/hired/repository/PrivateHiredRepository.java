package com.alseyahat.app.feature.vehicle.hired.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.vehicle.hired.repository.entity.PrivateHired;

public interface PrivateHiredRepository extends PagingAndSortingRepository<PrivateHired, Long>, QuerydslPredicateExecutor<PrivateHired> {

}
