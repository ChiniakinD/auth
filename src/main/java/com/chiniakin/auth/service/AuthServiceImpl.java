package com.chiniakin.auth.service;

import com.chiniakin.auth.entity.User;
import com.chiniakin.auth.enums.RoleEnum;
import com.chiniakin.auth.exception.BadRequestException;
import com.chiniakin.auth.exception.UserException;
import com.chiniakin.auth.exception.WrongPasswordException;
import com.chiniakin.auth.model.SignInUserRequest;
import com.chiniakin.auth.model.SignUpUserRequest;
import com.chiniakin.auth.repository.RoleRepository;
import com.chiniakin.auth.repository.UserRepository;
import com.chiniakin.auth.service.interfaces.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для регистрации и аутентификации пользователей.
 *
 * @author ChiniakinD
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    /**
     * Выполняет регистрацию нового пользователя.
     *
     * @param userInfoRequest модель для регистрации.
     */
    @Override
    public void signUp(SignUpUserRequest userInfoRequest) {
        checkUser(userInfoRequest);
        User user = mapper.map(userInfoRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByRole(RoleEnum.USER));
        userRepository.save(user);

    }

    /**
     * Выполняет аутентификацию и генерирует JWT токен.
     *
     * @param signInUserRequest модель для аутентификации.
     * @param response          ответ для добавления JWT токена в cookie.
     * @return токен в строковом виде.
     */
    @Override
    public String signIn(SignInUserRequest signInUserRequest, HttpServletResponse response) {
        checkAuthInformation(signInUserRequest);
        User user = mapper.map(signInUserRequest, User.class);
        String jwt = jwtService.generateToken(user);
        generateCookie(response, jwt);
        return jwt;
    }

    /**
     * Создает и добваляет JWT токент в cookie.
     *
     * @param response ответ для добавления токена.
     * @param jwt      токен.
     */
    private void generateCookie(HttpServletResponse response, String jwt) {
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * Проверяет данные на корректность при регистрации.
     *
     * @param userInfoRequest данные для регистрации.
     */
    private void checkUser(SignUpUserRequest userInfoRequest) {
        if (userRepository.existsByLogin(userInfoRequest.getLogin()) || userRepository.existsByEmail(userInfoRequest.getEmail())) {
            throw new UserException("Пользователь уже зарегистрирован");
        }
        if (userInfoRequest.getPassword().length() < 6 || !userInfoRequest.getPassword().matches("^[a-zA-Z0-9]+$")) {
            throw new BadRequestException("Uncorrected password");
        }
        if (userInfoRequest.getLogin().length() < 6 || !userInfoRequest.getLogin().matches("^[a-zA-Z0-9]+$")) {
            throw new BadRequestException("Uncorrected username");
        }
        if (!EmailValidator.getInstance().isValid(userInfoRequest.getEmail())) {
            throw new BadRequestException("Uncorrected email");
        }
    }

    /**
     * Проверяет данные при аутентификации.
     *
     * @param signInUserRequest модель для аутентификации.
     */
    private void checkAuthInformation(SignInUserRequest signInUserRequest) {
        User user = userRepository.findUserByLogin(signInUserRequest.getLogin()).orElseThrow(
                () -> new UserException("Пользователя с логином " + signInUserRequest.getLogin() + " не существует."));
        if (!passwordEncoder.matches(signInUserRequest.getPassword(), user.getPassword())) {
            throw new WrongPasswordException("Неправильный пароль");
        }
    }

}
