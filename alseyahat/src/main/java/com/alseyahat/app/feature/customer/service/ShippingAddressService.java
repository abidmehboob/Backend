package com.alseyahat.app.feature.customer.service;

import com.alseyahat.app.feature.customer.repository.entity.ShippingAddress;
import com.querydsl.core.types.Predicate;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShippingAddressService {

    ShippingAddress save(final ShippingAddress entity);

    ShippingAddress findOne(final Predicate predicate);
    
    Optional<ShippingAddress> find_One(Predicate predicate);

    Page<ShippingAddress> findAll(final Predicate predicate, final Pageable pageable);

}