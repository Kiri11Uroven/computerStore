package com.springteam.computerstore.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "PC Store Api",
        description = "Products manage system", version = "1.0.0",
        contact = @Contact(
            name = "Kirill, Artem, Dmitriy",
            email = "ila5264789@narod.ru, ranqest@gmail.com, dimon550@gmail.com"
        )
    )
)
public class ApiConfiguration {
}
