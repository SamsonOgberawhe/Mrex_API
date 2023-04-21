package com.mrex.mrexapi.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phoneNumber;

}
