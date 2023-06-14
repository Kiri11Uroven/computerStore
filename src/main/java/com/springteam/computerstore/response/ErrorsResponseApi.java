package com.springteam.computerstore.response;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public record ErrorsResponseApi(List<ErrorDto> errors) {

    public ErrorsResponseApi(@NotNull List<ErrorDto> errors) {
        this.errors = Objects.requireNonNull(errors);
    }
}
