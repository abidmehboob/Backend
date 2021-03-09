package com.alseyahat.app.commons;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.stereotype.Service;

@Service
public class OTPHelper {
	
	public String generateOTP() {
		String secret = "secret";
		Totp generator = new Totp(secret);
		return generator.now();
	}

}