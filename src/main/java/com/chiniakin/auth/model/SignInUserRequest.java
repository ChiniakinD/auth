package com.chiniakin.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Модель для аутентификации пользователя.")
public class SignInUserRequest {

    @Schema(name = "Логин пользователя.")
    private String login;

    @Schema(name = "Пароль пользователя.")
    private String password;

}
