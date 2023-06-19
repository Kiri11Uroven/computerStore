package com.springteam.computerstore.controller;

import com.springteam.computerstore.common.ProductType;
import com.springteam.computerstore.request.ProductCreationRequest;
import com.springteam.computerstore.response.DataResponseApi;
import com.springteam.computerstore.response.data.IdData;
import com.springteam.computerstore.response.data.ProductData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Product Api", description = "Api для работы с товарами.")
public interface ProductsApi {
    @Operation(summary = "Добавить новый продукт.")
    DataResponseApi<IdData> addProduct(ProductCreationRequest creationRequest);

    @Operation(summary = "Получить продукт по идентификатору.")
    DataResponseApi<ProductData> getById(@Parameter(description = "Идентификатор товара")Integer id);

    @Operation(summary = "Изменить продукт.")
    DataResponseApi<ProductData> updateProduct(@Parameter(description = "Идентификатор товара")Integer id, ProductCreationRequest request);

    @Operation(summary = "Получить список продуктов по типу товара.")
    DataResponseApi<List<ProductData>> getByType(@Parameter(description = "Тип товара")ProductType type);
}
