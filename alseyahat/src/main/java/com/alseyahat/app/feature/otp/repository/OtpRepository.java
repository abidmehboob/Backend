package com.alseyahat.app.feature.otp.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alseyahat.app.feature.otp.repository.entity.TwoFactorAuth;

public interface OtpRepository extends PagingAndSortingRepository<TwoFactorAuth, Long>, QuerydslPredicateExecutor<TwoFactorAuth> {

}

