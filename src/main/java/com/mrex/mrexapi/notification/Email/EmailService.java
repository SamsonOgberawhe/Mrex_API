package com.mrex.mrexapi.notification.Email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}


