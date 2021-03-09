package com.alseyahat.app.feature.customer.service.impl;


import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.customer.repository.CustomerRepository;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.customer.service.CustomerService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {

    PasswordEncoder encoder;

    CustomerRepository customerRepository;

    @Override
    public Customer findOne(final Predicate predicate) {
        return customerRepository.findOne(predicate)
                .orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.customer_entity_not_found"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer save(final Customer entity) {
        entity.setPassword(encoder.encode(entity.getPhone()));
        return customerRepository.save(entity);
    }
    
    @Override
    public Optional<Customer> find_One(final Predicate predicate) {
        return customerRepository.findOne(predicate);
    }

    @Override
    public boolean exist(final Predicate predicate) {
        return customerRepository.exists(predicate);
    }

    @Override
    public Page<Customer> findAll(final Predicate predicate, final Pageable pageable) {
        return customerRepository.findAll(predicate, pageable);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
	public Customer saveWithRollback(Customer customer) {
		return customerRepository.save(customer);
	}
}