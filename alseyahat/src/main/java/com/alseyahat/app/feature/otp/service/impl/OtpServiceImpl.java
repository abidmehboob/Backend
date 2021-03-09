package com.alseyahat.app.feature.otp.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.otp.repository.OtpRepository;
import com.alseyahat.app.feature.otp.repository.entity.TwoFactorAuth;
import com.alseyahat.app.feature.otp.service.OtpService;
import com.querydsl.core.types.Predicate;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OtpServiceImpl implements OtpService {

	OtpRepository otpRepository;

	@Override
	public TwoFactorAuth save(TwoFactorAuth entity) {
		return otpRepository.save(entity);
	}

	@Override
	public TwoFactorAuth findOne(Predicate predicate) {
		return otpRepository.findOne(predicate).orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.store_entity_not_found"));
	}

	@Override
	public Optional<TwoFactorAuth> find_One(Predicate predicate) {
		return otpRepository.findOne(predicate);
	}

	@Override
	public Page<TwoFactorAuth> findAll(Predicate predicate, Pageable pageable) {
		return otpRepository.findAll(predicate, pageable);
	}
	
	
}
