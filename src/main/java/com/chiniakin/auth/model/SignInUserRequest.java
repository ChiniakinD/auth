package com.chiniakin.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignInUserRequest {

    private String login;

    private String password;

}
