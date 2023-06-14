package com.springteam.computerstore.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDto(Integer code, String title, String detail) {
}
