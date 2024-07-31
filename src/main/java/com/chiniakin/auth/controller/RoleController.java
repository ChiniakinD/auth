package com.chiniakin.auth.controller;

import com.chiniakin.auth.model.RoleModel;
import com.chiniakin.auth.service.interfaces.RoleService;
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
public class RoleController {

    private final RoleService roleService;

    /**
     * Выполняет сохранение перечня ролей пользователю.
     *
     * @param roleModel модель для сохранения.
     * @param request   запрос для авторизации.
     */
    @PutMapping("/save")
    public void saveRole(@RequestBody RoleModel roleModel, HttpServletRequest request) {
        roleService.saveRole(roleModel, request);
    }

}

