package com.chiniakin.auth.service.interfaces;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.model.RoleModel;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

/**
 * Интерфейс сервиса для работы с ролями пользователей.
 */
public interface RoleService {

    /**
     * Выполняет сохранение ролей пользователя.
     *
     * @param roleModel модельс ролями.
     * @param request   запрос для получения токена.
     */
    void saveRole(RoleModel roleModel, HttpServletRequest request);

    /**
     * Выполняет получения ролей пользователя по его логину.
     *
     * @param login   логин пользователя.
     * @param request запрос для получения токена.
     * @return роли пользователя.
     */
    Set<Role> getRoles(String login, HttpServletRequest request);

}
