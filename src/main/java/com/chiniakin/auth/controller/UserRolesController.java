package com.chiniakin.auth.controller;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.service.interfaces.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/user-roles")
public class UserRolesController {

    private final RoleService roleService;

    @GetMapping("/{login}")
    public Set<Role> getRoles(@PathVariable String login, HttpServletRequest request) {
        return roleService.getRoles(login, request);
    }

}
