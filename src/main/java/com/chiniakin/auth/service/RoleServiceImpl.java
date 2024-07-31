package com.chiniakin.auth.service;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.entity.User;
import com.chiniakin.auth.enums.RoleEnum;
import com.chiniakin.auth.exception.AccessDeniedException;
import com.chiniakin.auth.exception.NotAuthorizedException;
import com.chiniakin.auth.model.RoleModel;
import com.chiniakin.auth.repository.RoleRepository;
import com.chiniakin.auth.repository.UserRepository;
import com.chiniakin.auth.service.interfaces.RoleService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

/**
 * Реализация сервиса для работы с ролями пользователей.
 *
 * @author ChiniakinD
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JWTService jwtService;

    /**
     * Сохраняет роли указанному пользователю.
     *
     * @param roleModel модель с ролями.
     * @param request   запрос для получения токена.
     */
    @Override
    public void saveRole(RoleModel roleModel, HttpServletRequest request) {
        String adminLogin = jwtService.extractUserName(getJWTToken(request));
        userRepository.findAdminUserByLoginOrThrow(adminLogin);
        User changedUser = userRepository.findUserByLoginOrThrow(roleModel.getLogin());
        Set<Role> roles = changedUser.getRoles();
        for (RoleEnum roleEnum : roleModel.getRoles()) {
            Role role = roleRepository.findByRole(roleEnum);
            roles.add(role);
        }
        changedUser.setRoles(roles);
        userRepository.save(changedUser);
    }

    /**
     * Получает роли пользователя по его логину.
     *
     * @param login   логин пользователя.
     * @param request запрос для получения токена.
     * @return роли пользователя.
     */
    @Override
    public Set<Role> getRoles(String login, HttpServletRequest request) {
        userRepository.findUserByLoginOrThrow(login);
        String currentUserLogin = jwtService.extractUserName(getJWTToken(request));
        if (!login.equals(currentUserLogin) && userRepository.findAdminUserByLogin(currentUserLogin).isEmpty()) {
            throw new AccessDeniedException("Отсутствуют права доступа");
        }
        return userRepository.findRolesByLogin(login);
    }

    /**
     * Получет токен из запроса.
     *
     * @param request запрос, содержащий cookie.
     * @return JWT токен
     */
    private String getJWTToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new NotAuthorizedException("Отсутствует авторизация");
        }
        return Arrays.stream(cookies)
                .filter(cookie -> "jwt".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(() -> new NotAuthorizedException("JWT token не найден"));
    }

}
