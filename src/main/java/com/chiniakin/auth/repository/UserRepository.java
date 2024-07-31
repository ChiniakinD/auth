package com.chiniakin.auth.repository;

import com.chiniakin.auth.entity.Role;
import com.chiniakin.auth.entity.User;
import com.chiniakin.auth.exception.AccessDeniedException;
import com.chiniakin.auth.exception.UserException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Репозиторий для работы с пользователями.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Получает пользователя по логину.
     *
     * @param login логин пользователя.
     */
    Optional<User> findUserByLogin(String login);

    /**
     * Получает пользователя по логину, если у него есть права администратора.
     *
     * @param login логин пользователя.
     * @return
     */
    @Query(value = "select u.id, u.email, u.login, u.password from users u " +
            "left join user_roles ur on ur.user_id = u.id " +
            "left join roles r on ur.role_id = r.id " +
            "where u.login = :login and ('ADMIN' in (r.role))", nativeQuery = true)
    Optional<User> findAdminUserByLogin(@Param("login") String login);

    /**
     * Выполняет проверку существования пользователя с данным логином.
     *
     * @param login логин пользователя.
     * @return true, если пользователь существует, иначе false.
     */
    boolean existsByLogin(String login);

    /**
     * Выполняет проверку существования пользователя с данным email.
     *
     * @param email email пользователя.
     * @return true, если пользователь существует, иначе false.
     */
    boolean existsByEmail(String email);

    /**
     * Получает пользователя с ролью администратора или выбрасывает исключение.
     *
     * @param login логин пользователя.
     */
    default void findAdminUserByLoginOrThrow(String login) {
        findAdminUserByLogin(login).orElseThrow(() -> new AccessDeniedException("Необходимы права администратора"));
    }

    /**
     * Получает пользователя по логину или выбрасывает исключение.
     *
     * @param login логин пользователя.
     * @return
     */
    default User findUserByLoginOrThrow(String login) {
        return findUserByLogin(login).orElseThrow(() -> new UserException("Пользователь не найден"));
    }

    /**
     * Получет роли пользователя по его логину.
     *
     * @param login логин пользователя.
     * @return роли пользователя.
     */
    @Query(value = "select distinct r from Role r " +
            "join UserRoles ur on r.id = ur.role.id " +
            "join User u on ur.user.id = u.id " +
            "where u.login = :login")
    Set<Role> findRolesByLogin(@Param("login") String login);

}
