package com.chiniakin.auth.service.interfaces;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.model.RoleModel;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

public interface RoleService {

    void saveRole(RoleModel roleModel, HttpServletRequest request);

    Set<Role> getRoles(String login, HttpServletRequest request);

}
