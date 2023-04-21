package com.mrex.mrexapi.service;

import com.mrex.mrexapi.dto.Response.SignupResponse;
import com.mrex.mrexapi.dto.request.SignupRequest;
import com.mrex.mrexapi.utils.ApiResponse;

public interface UserService {
     ApiResponse<SignupResponse> signup(SignupRequest signupRequest);
     ApiResponse<SignupResponse> updateUser(SignupRequest signupRequest);

     ApiResponse<SignupResponse> findUserById(Long id);

    ApiResponse<Object> deleteUser(Long id);
}
