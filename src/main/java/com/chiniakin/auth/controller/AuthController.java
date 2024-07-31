package com.chiniakin.auth.controller;

import com.chiniakin.auth.model.SignInUserRequest;
import com.chiniakin.auth.model.SignUpUserRequest;
import com.chiniakin.auth.service.interfaces.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PutMapping("/signup")
    public void signup(@RequestBody SignUpUserRequest userInfoRequest) {
        authService.signUp(userInfoRequest);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody SignInUserRequest signInUserRequest, HttpServletResponse response) {
        return authService.signIn(signInUserRequest, response);
    }

}
