package com.chiniakin.auth.controller;

import com.chiniakin.auth.model.SignInUserRequest;
import com.chiniakin.auth.model.SignUpUserRequest;
import com.chiniakin.auth.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для регистрации и аутентификации пользователей.
 *
 * @author ChiniakinD
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "Контроллер для регистрации и аутентификации пользователей.")
public class AuthController {

    private final AuthService authService;

    /**
     * Выполняет регистрацию нового пользователя.
     *
     * @param userInfoRequest модель для регистрации.
     */
    @Operation(summary = "Регистрация нового пользователя", description = "Создает нового пользователя согласно модели.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Неверные данные для регистрации или такойпользователь уже существует.")
    })
    @PutMapping("/signup")
    public void signup(@RequestBody SignUpUserRequest userInfoRequest) {
        authService.signUp(userInfoRequest);
    }

    /**
     * Выполняет аутентификацию пользователя и возвращает JWT токен.
     *
     * @param signInUserRequest модель для входа.
     * @param response          для установки JWT токена в cookie.
     * @return JWT токен.
     */
    @Operation(summary = "Аутентификация пользователя", description = "Возвращает токен при успешной аутентификации.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аутентификация прошла успешно"),
            @ApiResponse(responseCode = "400", description = "Пользователя с таким логином не существует."),
            @ApiResponse(responseCode = "401", description = "Неверный пароль.")
    })
    @PostMapping("/signin")
    public String signIn(@RequestBody SignInUserRequest signInUserRequest, HttpServletResponse response) {
        return authService.signIn(signInUserRequest, response);
    }

}
