package com.chiniakin.auth.entity;

import com.chiniakin.auth.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

/**
 * Роль пользователя.
 *
 * @author ChiniakinD
 */
@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
