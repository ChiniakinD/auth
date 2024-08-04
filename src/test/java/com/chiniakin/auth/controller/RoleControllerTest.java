package com.chiniakin.auth.controller;

import com.chiniakin.auth.TestBeans;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class RoleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("/insertForTests/insert.sql")
    void saveRoleWithoutADMINShouldBeForbidden() throws Exception {
        MvcResult result = returnJwtTokenAfterSignIn("1", "user_password");
        String jwtToken = result.getResponse().getCookie("jwt").getValue();
        mockMvc.perform(put("/roles/save")
                        .cookie(new Cookie("jwt", jwtToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                          "login": "1",
                                          "roles": ["SUPERUSER", "ADMIN"]
                                      }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void saveRoleWithoutAuthorizationShouldFail() throws Exception {
        mockMvc.perform(put("/roles/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                          "login": "123456",
                                          "roles": ["SUPERUSER", "ADMIN"]
                                      }
                                """))
                .andExpect(status().isUnauthorized());
    }

    private MvcResult returnJwtTokenAfterSignIn(String login, String password) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("""
                              {
                                  "login": "%s",
                                  "password": "%s"
                              }
                        """, login, password))).andReturn();
    }

}

