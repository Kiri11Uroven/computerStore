package com.springteam.computerstore.controller;

import com.springteam.computerstore.common.ProductType;
import com.springteam.computerstore.request.ProductCreationRequest;
import com.springteam.computerstore.response.DataResponseApi;
import com.springteam.computerstore.response.data.IdData;
import com.springteam.computerstore.response.data.ProductData;

import java.util.List;

public interface ProductsApi {
    DataResponseApi<IdData> addProduct(ProductCreationRequest creationRequest);

    DataResponseApi<ProductData> getById(Integer id);

    DataResponseApi<ProductData> updateProduct(Integer id, ProductCreationRequest request);

    DataResponseApi<List<ProductData>> getByType(ProductType type);
}
