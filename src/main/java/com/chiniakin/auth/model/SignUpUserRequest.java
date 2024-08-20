package com.chiniakin.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Модель для регистрации нового пользователя.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Schema(name = "Модель для регистрации пользователя.")
public class SignUpUserRequest {

    @Schema(name = "Логин пользователя.")
    private String login;

    @Schema(name = "Пароль пользователя.")
    private String password;

    @Schema(name = "Email пользователя.")
    private String email;

}
