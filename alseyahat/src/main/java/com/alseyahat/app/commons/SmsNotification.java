package com.alseyahat.app.commons;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Service
public class SmsNotification {
//	@Value("${smsSender.sid}")
//	static
//	String smsSenderSid;
//	
//	@Value("${smsSender.token}")
//	static
//	String smsSenderToken;
	
//	@Value("${smsSender.number}")
	String smsSenderNumber="+12028310963";
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID ="AC99aefec72f6daa53222826616d40dd75";
    public static final String AUTH_TOKEN ="ae747a44f607a8b215b447ebe88582e9";

    public String sendSms(String toNumber, String msg) {
    	 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	 Message message = Message.creator(new PhoneNumber(toNumber), // to
                         new PhoneNumber(smsSenderNumber), // from
                         msg).create();

		return message.getSid();
    }
    
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message
//                .creator(new PhoneNumber("+14159352345"), // to
//                        new PhoneNumber("+14158141829"), // from
//                        "Where's Wallace?")
//                .create();
//
//        System.out.println(message.getSid());
//    }
}
