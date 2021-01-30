package com.alseyahat.app.feature.customer.service;

import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer save(final Customer entity);

    boolean exist(final Predicate predicate);

    Customer findOne(final Predicate predicate);

    Page<Customer> findAll(final Predicate predicate, final Pageable pageable);

}
