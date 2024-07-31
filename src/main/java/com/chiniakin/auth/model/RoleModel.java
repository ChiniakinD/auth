package com.chiniakin.auth.model;

import com.chiniakin.auth.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Модель для сохранения ролей пользователя.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
public class RoleModel {

    private String login;

    private Set<RoleEnum> roles;

}
