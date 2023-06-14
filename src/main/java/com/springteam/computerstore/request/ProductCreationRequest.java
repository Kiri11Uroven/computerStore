package com.springteam.computerstore.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springteam.computerstore.common.ProductType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreationRequest(
    @NotNull
    @JsonProperty("serial-number")
    String serialNumber,

    @NotNull
    String manufacturer,

    @NotNull
    BigDecimal price,

    @NotNull
    Integer amount,

    @NotNull
    ProductType type,

    @Nullable
    @JsonProperty("properties")
    String additionalProperty
) {
}
