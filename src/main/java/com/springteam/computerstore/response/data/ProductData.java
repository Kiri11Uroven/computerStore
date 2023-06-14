package com.springteam.computerstore.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springteam.computerstore.common.ProductType;

import java.math.BigDecimal;

public record ProductData(
    int id,

    @JsonProperty("serial-number")
    String serialNumber,

    String manufacturer,

    BigDecimal price,

    int amount,

    ProductType type,

    @JsonProperty("properties")
    String additionalProperty
) {
}
