package com.mrex.mrexapi.notification.Email;

import com.mrex.mrexapi.notification.SmsService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender javaMailSender;
    private final SmsService smsService;
    private final Environment env;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom(env.getProperty("EMAIL_USERNAME"));
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setSubject(details.getSubject());
            mailMessage.setText(details.getMsgBody());
            javaMailSender.send(mailMessage);

            log.info("Mail sent successfully\n");
            return "Mail sent successfully";
        }catch (Exception e){
            log.error("Error sending mail", e);
            return "Error sending mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {


        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(env.getProperty("EMAIL_PASSWORD"));
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody());

            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
            helper.addAttachment(file.getFilename(), file);

            javaMailSender.send(message);
            log.debug("Mail sent successfully");
            return "Mail sent successfully";
        } catch (MessagingException e) {
            log.error("Error sending mail", e);
            return "Error sending mail";
        }
    }
}
