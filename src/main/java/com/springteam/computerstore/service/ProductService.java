package com.springteam.computerstore.service;

import com.springteam.computerstore.entity.ProductType;
import com.springteam.computerstore.dto.request.ProductCreationRequest;
import com.springteam.computerstore.dto.response.data.IdData;
import com.springteam.computerstore.dto.response.data.ProductData;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    IdData createProduct(@Valid ProductCreationRequest request);
    ProductData getProduct(@Valid Integer id);
    ProductData updateProductById(@Valid Integer id,ProductCreationRequest request);
    List<ProductData> getProductsByType(@Valid ProductType type);
}
