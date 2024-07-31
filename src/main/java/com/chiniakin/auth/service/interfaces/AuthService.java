package com.chiniakin.auth.service.interfaces;

import com.chiniakin.auth.model.SignInUserRequest;
import com.chiniakin.auth.model.SignUpUserRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void signUp(SignUpUserRequest userInfoRequest);

    String signIn(SignInUserRequest signInUserRequest, HttpServletResponse response);

}
