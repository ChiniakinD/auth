package com.chiniakin.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Проект аутентификации и авторизации пользователей",
                contact = @Contact(
                        name = "ChiniakinD",
                        url = "https://github.com/ChiniakinD/auth"
                )
        )
)

public class OpenApiConfig {
}
