package com.chiniakin.auth.controller;

import com.chiniakin.auth.TestBeans;
import com.chiniakin.auth.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class UserRolesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("/insertForTests/insert.sql")
    void getSelfRolesShouldBeSuccessful() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                              {
                                  "login": "1",
                                  "password": "user_password"
                              }
                        """)).andReturn();

        String jwtToken = result.getResponse().getCookie("jwt").getValue();
        mockMvc.perform(get("/user-roles/1")
                        .cookie(new Cookie("jwt", jwtToken))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void getUserRolesWithoutAuthorizationShouldFail() throws Exception {
        mockMvc.perform(get("/user-roles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}

