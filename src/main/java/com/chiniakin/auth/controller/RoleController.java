package com.chiniakin.auth.controller;

import com.chiniakin.auth.model.RoleModel;
import com.chiniakin.auth.service.interfaces.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с ролями пользователей.
 *
 * @author ChiniakinD
 */
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "RoleController", description = "Контроллер для работы с ролями пользователей.")
public class RoleController {

    private final RoleService roleService;

    /**
     * Выполняет сохранение перечня ролей пользователю.
     *
     * @param roleModel модель для сохранения.
     * @param request   запрос для авторизации.
     */
    @Operation(summary = "Сохранение ролей пользователю", description = "Сохраняет роли пользователю согласно модели.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роли сохранены."),
            @ApiResponse(responseCode = "401", description = "Отсутствует авторизация."),
            @ApiResponse(responseCode = "403", description = "Необходимы права администратора.")
    })
    @PutMapping("/save")
    public void saveRole(@RequestBody RoleModel roleModel, HttpServletRequest request) {
        roleService.saveRole(roleModel, request);
    }

}

