package com.chiniakin.auth;

import com.chiniakin.auth.exception.NotAuthorizedException;
import com.chiniakin.auth.service.RoleServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
    }

    @Test
    void getJwtTokenShouldCorrectReturnToken() {
        String expectedJwt = "jwtToken";
        Cookie[] cookies = {new Cookie("jwt", expectedJwt)};
        when(request.getCookies()).thenReturn(cookies);
        String jwt = roleService.getJWTToken(request);
        assertEquals(expectedJwt, jwt);
    }

    @Test
    void getJWTTokenWithoutCookieShouldThrowsNotAuthorizedException() {
        when(request.getCookies()).thenReturn(null);
        assertThrows(NotAuthorizedException.class, () -> roleService.getJWTToken(request));
    }

}
