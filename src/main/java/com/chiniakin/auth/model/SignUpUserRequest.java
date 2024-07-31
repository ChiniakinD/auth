package com.chiniakin.auth.model;

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
public class SignUpUserRequest {

    private String login;

    private String password;

    private String email;

}
