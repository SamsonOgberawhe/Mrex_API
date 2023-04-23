package com.mrex.mrexapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "twilio.config")
public class TwilioConfigData {
    private String accountSid;
    private String authToken;
    private String phoneNumber;
    private String username;
    private String password;

}
