package com.alseyahat.app.feature.customer.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alseyahat.app.feature.customer.repository.entity.ShippingAddress;

public interface ShippingAddressRepository extends PagingAndSortingRepository<ShippingAddress, Long>, QuerydslPredicateExecutor<ShippingAddress> {

}

