package com.mrex.mrexapi.service.serviceImpl;

import com.mrex.mrexapi.dto.Response.SignupResponse;
import com.mrex.mrexapi.dto.request.SignupRequest;
import com.mrex.mrexapi.entity.User;
import com.mrex.mrexapi.repository.UserRepository;
import com.mrex.mrexapi.service.UserService;
import com.mrex.mrexapi.utils.ApiResponse;
import com.mrex.mrexapi.utils.ResponseCodes;
import com.mrex.mrexapi.utils.Validator;
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
                    ResponseCodes.EMAIL_ALREADY_EXISTS, "User already exists", null);
        }
        if(!(userRepository.findByUsername(signupRequest.getUsername()) == null)){
            return new ApiResponse<>(
                    ResponseCodes.USERNAME_ALREADY_EXISTS, "Username already exists", null);
        }
        if(!(Validator.isPasswordValid(signupRequest.getPassword()))){
            return new ApiResponse<>(
                    ResponseCodes.INVALID_PASSWORD, "Password is not valid. Password must contain " +
                    "upper and lower case and special characters", null);
        }
        if(!(Validator.isEmailValid(signupRequest.getEmail()))){
            return new ApiResponse<>(
                    ResponseCodes.INVALID_EMAIL, "Email is not valid. Please enter a valid email", null);
        }
        try {
            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setEmail(signupRequest.getEmail());
            user.setPassword(signupRequest.getPassword());
            user.setPhoneNumber(signupRequest.getPhoneNumber());
            user.setUsername(signupRequest.getUsername());

            signupResponse.setFirstName(signupRequest.getFirstName());
            signupResponse.setLastName(signupRequest.getLastName());
            signupResponse.setEmail(signupRequest.getEmail());
            signupResponse.setPhoneNumber(signupRequest.getPhoneNumber());
            signupResponse.setUsername(signupRequest.getUsername());

            userRepository.save(user);
            return new ApiResponse<>(ResponseCodes.SUCCESS_STATUS, "User created successfully", signupResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public ApiResponse<SignupResponse> updateUser(SignupRequest signupRequest) {
        SignupResponse signupResponse = new SignupResponse();
        if(userRepository.findByEmail(signupRequest.getEmail()) == null){
            return new ApiResponse<>(
                    ResponseCodes.USER_NOT_FOUND, "User not found", null);
        }

        if(!(Validator.isPasswordValid(signupRequest.getPassword()))){
            return new ApiResponse<>(
                    ResponseCodes.INVALID_PASSWORD, "Password is not valid. Password must contain " +
                    "upper and lower case and special characters", null);
        }
        if(!(Validator.isEmailValid(signupRequest.getEmail()))){
            return new ApiResponse<>(
                    ResponseCodes.INVALID_EMAIL, "Email is not valid. Please enter a valid email", null);
        }

        User user = userRepository.findByEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setUsername(signupRequest.getUsername());
        user.setPhoneNumber(signupRequest.getPhoneNumber());
        user.setPassword(signupRequest.getPassword());

        signupResponse.setFirstName(user.getFirstName());
        signupResponse.setLastName(user.getLastName());
        signupResponse.setEmail(user.getEmail());
        signupResponse.setPhoneNumber(user.getPhoneNumber());
        signupResponse.setUsername(user.getUsername());

        userRepository.save(user);
        return new ApiResponse<>(ResponseCodes.SUCCESS_STATUS, "User updated successfully", signupResponse);
    }

    @Override
    public ApiResponse<SignupResponse> findUserById(Long id) {
        SignupResponse signupResponse = new SignupResponse();
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return new ApiResponse<>(
                    ResponseCodes.USER_NOT_FOUND, "User not found", null);
        }
        signupResponse.setFirstName(user.getFirstName());
        signupResponse.setLastName(user.getLastName());
        signupResponse.setEmail(user.getEmail());
        signupResponse.setPhoneNumber(user.getPhoneNumber());
        signupResponse.setUsername(user.getUsername());

        return new ApiResponse<>(ResponseCodes.SUCCESS_STATUS, "User found", signupResponse);

    }

    @Override
    public ApiResponse<Object> deleteUser(Long id) {

        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return new ApiResponse<>(
                    ResponseCodes.USER_NOT_FOUND, "User not found", null);
        }
        userRepository.delete(user);
        return new ApiResponse<>(ResponseCodes.SUCCESS_STATUS, "User deleted successfully", null);
    }
}
