package com.alseyahat.app.feature.customer.service.impl;


import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.customer.repository.ShippingAddressRepository;
import com.alseyahat.app.feature.customer.repository.entity.ShippingAddress;
import com.alseyahat.app.feature.customer.service.ShippingAddressService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ShippingAddressServiceImpl implements ShippingAddressService {

    ShippingAddressRepository shippingAddressRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShippingAddress save(final ShippingAddress entity) {
        return shippingAddressRepository.save(entity);
    }

    @Override
    public ShippingAddress findOne(Predicate predicate) {
        return shippingAddressRepository.findOne(predicate)
                .orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.shipping_address_entity_not_found"));
    }
    
    @Override
    public Optional<ShippingAddress> find_One(Predicate predicate) {
        return shippingAddressRepository.findOne(predicate);
    }

    @Override
    public Page<ShippingAddress> findAll(Predicate predicate, Pageable pageable) {
        return shippingAddressRepository.findAll(predicate, pageable);
    }
}
