package com.mrex.mrexapi.notification.Otp;

import com.mrex.mrexapi.entity.User;
import com.mrex.mrexapi.notification.Email.EmailDetails;
import com.mrex.mrexapi.notification.Email.EmailServiceImpl;
import com.mrex.mrexapi.notification.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class OtpService {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private SmsService smsService;
    public String generateOtp(){
        Random random = new Random();
        int otp = random.nextInt(999999);
        return Integer.toString(otp);
    }

    public void sendOtpEmail(String email, User user, String otp){
        EmailDetails details = new EmailDetails();
        details.setRecipient(email);
        details.setSubject("OTP for Mrex");
        details.setMsgBody("Hi "+user.getFirstName()+",\n\nYour OTP is "+otp+
                " and is valid for 10 minutes." +"\n\nRegards,\nMrex Team");

        emailService.sendSimpleMail(details);
    }
    public void sendOtpSms(String phoneNumber, User user, String otp){
        String message = "Dear "+user.getFirstName()+",\n\nYour OTP is "+otp+
                " and is valid for 10 minutes." +"\n\nRegards,\nMrex Team";
        smsService.sendSMS(phoneNumber, message);
    }
}
