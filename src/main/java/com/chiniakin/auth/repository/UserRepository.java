package com.chiniakin.auth.repository;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.entity.User;
import com.chiniakin.auth.exception.AccessDeniedException;
import com.chiniakin.auth.exception.UserNotExistException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    @Query(value = "select u.id, u.email, u.login, u.password from users u " +
            "left join user_roles ur on ur.user_id = u.id " +
            "left join roles r on ur.role_id = r.id " +
            "where u.login = :login and ('ADMIN' in (r.role))", nativeQuery = true)
    Optional<User> findAdminUserByLogin(@Param("login") String login);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    default void findAdminUserByLoginOrThrow(String login) {
        findAdminUserByLogin(login).orElseThrow(() -> new AccessDeniedException("Необходимы права администратора"));
    }

    default User findUserByLoginOrThrow(String login) {
        return findUserByLogin(login).orElseThrow(() -> new UserNotExistException("Пользователь не найден"));
    }

    @Query(value = "select distinct r from Role r " +
            "join UserRoles ur on r.id = ur.role.id " +
            "join User u on ur.user.id = u.id " +
            "where u.login = :login")
    Set<Role> findRolesByLogin(@Param("login") String login);

}
