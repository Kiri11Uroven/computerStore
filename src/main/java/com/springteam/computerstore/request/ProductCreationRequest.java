package com.springteam.computerstore.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springteam.computerstore.common.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Запрос на создание продукта.")
public record ProductCreationRequest(
    @NotNull
    @JsonProperty("serial-number")
    @Schema(description = "Номер серии.", example = "22-ВБА-87")
    String serialNumber,

    @NotBlank
    @Schema(description = "Производитель.", example = "Samsung")
    String manufacturer,

    @NotNull
    @Schema(description = "Цена за единицу продукта.")
    BigDecimal price,

    @NotNull
    @Schema(description = "Количество единиц продукта.")
    Integer amount,

    @NotNull
    @Schema(description = "Тип продукта.", example = "MONITOR")
    ProductType type,

    @Nullable
    @JsonProperty("properties")
    @Schema(description = "Дополнительный атрибут.", example = "17'")
    String additionalProperty
) {
}
