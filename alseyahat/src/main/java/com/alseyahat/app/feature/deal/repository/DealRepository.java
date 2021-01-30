package com.alseyahat.app.feature.deal.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.deal.repository.entity.Deal;

public interface DealRepository extends PagingAndSortingRepository<Deal, Long>, QuerydslPredicateExecutor<Deal> {

}

