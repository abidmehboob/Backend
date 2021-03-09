package com.alseyahat.app.feature.otp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alseyahat.app.feature.otp.dto.OtpCreateRequest;
import com.alseyahat.app.feature.otp.dto.OtpValidateRequest;
import com.alseyahat.app.feature.otp.facade.OtpFacade;

import lombok.AccessLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@Api(tags = "OTP")
@RequestMapping("/otp")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpController {
	OtpFacade otpFacade;
	
	@PostMapping("/validate")
    @ApiOperation(value = "Validate OTP", nickname = "validateOTP", notes = "Validate OTP")
    public ResponseEntity<Boolean> validateOTP(@Valid @RequestBody final OtpValidateRequest otpValidateRequest) {
        return new ResponseEntity<>(otpFacade.validateOTP(otpValidateRequest), HttpStatus.CREATED);
    }
	
	@PostMapping("/send")
    @ApiOperation(value = "Send OTP", nickname = "sendOTP", notes = "Send OTP")
    public ResponseEntity<Boolean> sendOTP(@Valid @RequestBody final OtpCreateRequest otpCreateRequest) {
        return new ResponseEntity<>(otpFacade.sendOTP(otpCreateRequest), HttpStatus.CREATED);
    }

}
