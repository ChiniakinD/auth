package com.chiniakin.auth.repository;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(RoleEnum roleEnum);

}
