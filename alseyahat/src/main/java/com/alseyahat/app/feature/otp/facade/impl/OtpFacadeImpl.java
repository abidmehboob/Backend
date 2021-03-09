package com.alseyahat.app.feature.otp.facade.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import org.apache.commons.lang3.time.DateUtils;
import com.alseyahat.app.commons.OTPHelper;
import com.alseyahat.app.commons.SmsNotification;
import com.alseyahat.app.feature.otp.dto.OtpCreateRequest;
import com.alseyahat.app.feature.otp.dto.OtpValidateRequest;
import com.alseyahat.app.feature.otp.facade.OtpFacade;
import com.alseyahat.app.feature.otp.repository.entity.QTwoFactorAuth;
import com.alseyahat.app.feature.otp.repository.entity.TwoFactorAuth;
import com.alseyahat.app.feature.otp.service.OtpService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OtpFacadeImpl implements OtpFacade {
	
	
	OtpService otpService;
    
  
	OTPHelper otpHelper;
    
  
	SmsNotification smsNotification;
    
	
    @Override
    @SneakyThrows
	public boolean validateOTP(OtpValidateRequest otpValidateRequest){
		Optional<TwoFactorAuth> twoFactorAuth = null;
		if(otpValidateRequest.getUserId().equals("")) {
			twoFactorAuth = otpService.find_One(QTwoFactorAuth.twoFactorAuth.phoneNumber.eq(otpValidateRequest.getNumber()).and(QTwoFactorAuth.twoFactorAuth.otpData.eq(otpValidateRequest.getOtpData())));
		}else
		twoFactorAuth = otpService.find_One(QTwoFactorAuth.twoFactorAuth.userId.eq(otpValidateRequest.getUserId()).and(QTwoFactorAuth.twoFactorAuth.phoneNumber.eq(otpValidateRequest.getNumber())).and(QTwoFactorAuth.twoFactorAuth.otpData.eq(otpValidateRequest.getOtpData())));
		
		if (!twoFactorAuth.isPresent())
			return false;
		else {
			SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
			Date time = format.parse(twoFactorAuth.get().getExpiresIn());
			Date currentTime = format.parse(new Date().toString());
			long diff = currentTime.getTime() - time.getTime();
			long diffMinutes = diff / (60 * 1000) % 60;
			if (!twoFactorAuth.get().getStatus().equalsIgnoreCase("I")) {
				return false;
			} else if (diffMinutes > 3 || !twoFactorAuth.get().getOtpData().equals(otpValidateRequest.getOtpData())) {
				return false;
			} else {
				twoFactorAuth.get().setStatus("C");
				twoFactorAuth.get().setConsumedDate(new Date().toString());
				otpService.save(twoFactorAuth.get());
			}
		}
	
		return true;
	}

	@Override
	public boolean sendOTP(OtpCreateRequest otpCreateRequest) {

		String otp = otpHelper.generateOTP();
	
		Date targetTime = new Date(); // now
//		targetTime = DateUtils.addMinutes(targetTime, 2); // add minute
		TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
		twoFactorAuth.setCreatedDate(new Date().toString());
		//twoFactorAuth.setConsumedDate(new Date().toString());
		twoFactorAuth.setEventTitle(otpCreateRequest.getEventTitle());
		targetTime = DateUtils.addMinutes(targetTime, 5); // add minute
		twoFactorAuth.setExpiresIn(targetTime.toString());
		twoFactorAuth.setPhoneNumber(otpCreateRequest.getNumber());
		twoFactorAuth.setOtpData(otp);;
		twoFactorAuth.setStatus("I");
		twoFactorAuth.setUserId(otpCreateRequest.getUserId());
		otpService.save(twoFactorAuth);
		smsNotification.sendSms(otpCreateRequest.getNumber(), otp);
	
		return true;
	}


}

