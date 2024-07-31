package com.chiniakin.auth.controller;

import com.chiniakin.auth.model.RoleModel;
import com.chiniakin.auth.service.interfaces.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PutMapping("/save")
    public void saveRole(@RequestBody RoleModel roleModel, HttpServletRequest request) {
        roleService.saveRole(roleModel, request);
    }

}

