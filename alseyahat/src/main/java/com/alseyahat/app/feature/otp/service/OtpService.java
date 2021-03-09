package com.alseyahat.app.feature.otp.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.otp.repository.entity.TwoFactorAuth;
import com.querydsl.core.types.Predicate;

public interface OtpService {

	TwoFactorAuth save(final TwoFactorAuth entity);

	TwoFactorAuth findOne(final Predicate predicate);

    Optional<TwoFactorAuth> find_One(final Predicate predicate);

    Page<TwoFactorAuth> findAll(final Predicate predicate, final Pageable pageable);


}
