package com.chiniakin.auth.model;

import com.chiniakin.auth.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Модель для сохранения перечня ролей пользователю.")
public class RoleModel {

    @Schema(name = "Логин пользователя.")
    private String login;

    @Schema(name = "Набор ролей пользователю.")
    private Set<RoleEnum> roles;

}
