package com.alseyahat.app.feature.otp.facade;

import com.alseyahat.app.feature.otp.dto.OtpCreateRequest;
import com.alseyahat.app.feature.otp.dto.OtpValidateRequest;

public interface OtpFacade {

	boolean validateOTP(OtpValidateRequest otpValidateRequest);

	boolean sendOTP(OtpCreateRequest otpCreateRequest);
}