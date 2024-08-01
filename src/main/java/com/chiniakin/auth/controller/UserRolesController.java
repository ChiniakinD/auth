package com.chiniakin.auth.controller;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.service.interfaces.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Контроллер для получения ролей пользователя.
 *
 * @author ChiniakinD
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user-roles")
@Tag(name = "UserRolesController", description = "Контроллер для получения ролей пользователей.")
public class UserRolesController {

    private final RoleService roleService;

    /**
     * Возвращает роли указанного пользователя.
     *
     * @param login   логин пользователя.
     * @param request запрос для авторизации.
     * @return роли пользвателя.
     */
    @Operation(summary = "Сохранение ролей пользователю", description = "Сохраняет роли пользователю согласно модели.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роли получены."),
            @ApiResponse(responseCode = "400", description = "Пользователь не найден."),
            @ApiResponse(responseCode = "401", description = "Отсутствует авторизация."),
            @ApiResponse(responseCode = "403", description = "Необходимы права администратора.")
    })
    @GetMapping("/{login}")
    public Set<Role> getRoles(@PathVariable String login, HttpServletRequest request) {
        return roleService.getRoles(login, request);
    }

}
