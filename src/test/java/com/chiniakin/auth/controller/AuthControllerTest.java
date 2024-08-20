package com.chiniakin.auth.controller;

import com.chiniakin.auth.TestBeans;
import com.chiniakin.auth.entity.User;
import com.chiniakin.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("/insertForTests/insert.sql")
    void addNewUserShouldBeSuccessfulAdded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                          "login": "123456",
                                          "password": "123456",
                                          "email": "123456@mail.ru"
                                      }
                                """))
                .andExpect(status().isOk());
        User user = userRepository.findUserByLoginOrThrow("123456");
        assertEquals("123456", user.getLogin());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void enterExistUserShouldBeSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                          "login": "1",
                                          "password": "user_password"
                                      }
                                """))
                .andExpect(status().isOk());
    }
}
