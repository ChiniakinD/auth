package com.chiniakin.auth;

import com.chiniakin.auth.entity.User;
import com.chiniakin.auth.exception.UserException;
import com.chiniakin.auth.exception.WrongPasswordException;
import com.chiniakin.auth.model.SignInUserRequest;
import com.chiniakin.auth.model.SignUpUserRequest;
import com.chiniakin.auth.repository.RoleRepository;
import com.chiniakin.auth.repository.UserRepository;
import com.chiniakin.auth.service.AuthServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUpExistUserShouldThrowsUserException() {
        SignUpUserRequest request = new SignUpUserRequest();
        request.setLogin("user123");
        request.setEmail("user@gmail.com");
        when(userRepository.existsByLogin(request.getLogin())).thenReturn(true);

        UserException exception = assertThrows(UserException.class, () -> authServiceImpl.signUp(request));
        assertEquals("Пользователь уже зарегистрирован", exception.getMessage());
    }

    @Test
    void signUpNewUserShouldSavesUser() {
        SignUpUserRequest request = new SignUpUserRequest();
        request.setLogin("user123");
        request.setEmail("user@gmail.com");
        request.setPassword("password123");
        User user = new User();
        when(userRepository.existsByLogin(request.getLogin())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(mapper.map(request, User.class)).thenReturn(user);
        authServiceImpl.signUp(request);
        verify(userRepository).save(user);
    }

    @Test
    void signInWithInvalidPasswordShouldThrowsWrongPasswordException() {
        SignInUserRequest request = new SignInUserRequest();
        request.setLogin("user123");
        request.setPassword("wrongpassword");
        User user = new User();
        when(userRepository.findUserByLogin(request.getLogin())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(false);

        WrongPasswordException exception = assertThrows(WrongPasswordException.class, () -> authServiceImpl.signIn(request, mock(HttpServletResponse.class)));
        assertEquals("Неправильный пароль", exception.getMessage());
    }

    @Test
    void generateCookieShouldCreateNewCookie() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String jwt = "jwtToken";
        authServiceImpl.generateCookie(response, jwt);
        verify(response).addCookie(any(Cookie.class));
    }

    @Test
    void checkAuthInformationWithValidCredentialsNotThrowsException() {
        SignInUserRequest request = new SignInUserRequest();
        request.setLogin("user123");
        request.setPassword("password123");
        User user = new User();
        when(userRepository.findUserByLogin(request.getLogin())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
        assertDoesNotThrow(() -> authServiceImpl.checkAuthInformation(request));
    }

}
