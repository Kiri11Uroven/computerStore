package com.springteam.computerstore.dto.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springteam.computerstore.entity.ProductType;
import lombok.Builder;

import java.math.BigDecimal;
@Builder
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
