package com.chiniakin.auth.repository;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с Ролями.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Возвращает роль по RoleEnum.
     */
    Role findByRole(RoleEnum roleEnum);

}
