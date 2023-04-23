package com.mrex.mrexapi.notification;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsService {
    @Value("${twilio.config.accountSid}")
    private String accountSid;
    @Value("${twilio.config.authToken}")
    private String authToken;
    @Value("${twilio.config.username}")
    private String username;
    @Value("${twilio.config.phoneNumber}")
    private String senderPhoneNumber;

    @Value("${twilio.config.password}")
    private String password;
    public String sendSMS(String receivingPhoneNumber, String messageBody) {

        try{
                Twilio.init(accountSid, authToken);
    //                Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));
    //
            Message.creator(new PhoneNumber(receivingPhoneNumber),
                    new PhoneNumber(senderPhoneNumber), messageBody).create();
            log.info("Sms sent successfully to " + receivingPhoneNumber);
            return "SMS sent successfully";
        }catch (Exception e){
            log.error("Error sending sms", e);
            return "Error sending sms";
        }
    }
}
