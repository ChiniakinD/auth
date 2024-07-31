package com.chiniakin.auth.service.interfaces;

import com.chiniakin.auth.model.SignInUserRequest;
import com.chiniakin.auth.model.SignUpUserRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Интерфейс сервиса для регистрации и аутентификации пользователей.
 */
public interface AuthService {

    /**
     * Выполняет регистрацию нового пользователя.
     *
     * @param userInfoRequest модель для регистрации.
     */
    void signUp(SignUpUserRequest userInfoRequest);

    /**
     * Выполняет аутентификацию пользователя.
     *
     * @param signInUserRequest модель для аутентификации.
     * @param response          ответ для добавления JWT токена в cookie.
     * @return JWT токен для аутентифицированного пользователя.
     */
    String signIn(SignInUserRequest signInUserRequest, HttpServletResponse response);

}
