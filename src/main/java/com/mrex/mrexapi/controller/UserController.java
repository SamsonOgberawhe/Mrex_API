package com.mrex.mrexapi.controller;

import com.mrex.mrexapi.dto.Response.SignupResponse;
import com.mrex.mrexapi.dto.request.SignupRequest;
import com.mrex.mrexapi.service.UserService;
import com.mrex.mrexapi.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController<T> {

    private final UserService userService;
    @PostMapping("")
    public ApiResponse<SignupResponse> signUp(@RequestBody SignupRequest request) {
        return userService.signup(request);
    }

    @PatchMapping("")
    public ApiResponse<SignupResponse> updateUser(@RequestBody SignupRequest request) {
        return userService.updateUser(request);
    }

    @GetMapping("/{id}")
    public ApiResponse<SignupResponse> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
