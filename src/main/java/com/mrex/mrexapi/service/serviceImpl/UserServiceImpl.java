package com.mrex.mrexapi.service.serviceImpl;

import com.mrex.mrexapi.dto.Response.SignupResponse;
import com.mrex.mrexapi.dto.request.SignupRequest;
import com.mrex.mrexapi.entity.User;
import com.mrex.mrexapi.repository.UserRepository;
import com.mrex.mrexapi.service.UserService;
import com.mrex.mrexapi.utils.ApiResponse;
import com.mrex.mrexapi.utils.ResponseCodes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ApiResponse<SignupResponse> signup(SignupRequest signupRequest) {
        SignupResponse signupResponse = new SignupResponse();
        ApiResponse<SignupResponse> response = new ApiResponse<>();
        response.setStatus(ResponseCodes.SYSTEM_ERROR);
        User user = new User();

        if (!(userRepository.findByEmail(signupRequest.getEmail()) == null)) {
            return new ApiResponse<>(
                    ResponseCodes.USER_ALREADY_EXISTS, "User already exists", null);
        }
        try {
            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setEmail(signupRequest.getEmail());
            user.setPassword(signupRequest.getPassword());
            user.setPhoneNumber(signupRequest.getPhoneNumber());
            user.setUsername(signupRequest.getUsername());
            userRepository.save(user);
            return new ApiResponse<>(ResponseCodes.SUCCESS_STATUS, "User created successfully", signupResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
