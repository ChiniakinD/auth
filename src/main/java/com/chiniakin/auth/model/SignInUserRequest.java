package com.chiniakin.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Модель для аутентификации пользователя.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SignInUserRequest {

    private String login;

    private String password;

}
