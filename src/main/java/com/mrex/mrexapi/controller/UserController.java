package com.mrex.mrexapi.controller;

import com.mrex.mrexapi.dto.Response.SignupResponse;
import com.mrex.mrexapi.dto.request.SignupRequest;
import com.mrex.mrexapi.entity.User;
import com.mrex.mrexapi.service.UserService;
import com.mrex.mrexapi.utils.ApiResponse;
import com.mrex.mrexapi.utils.ResponseCodes;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController<T> {

    private final UserService userService;
    @PostMapping("/signup")
    public ApiResponse<SignupResponse> signUp(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }
}
