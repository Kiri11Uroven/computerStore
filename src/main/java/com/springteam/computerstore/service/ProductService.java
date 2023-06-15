package com.springteam.computerstore.service;

import com.springteam.computerstore.common.ProductType;
import com.springteam.computerstore.request.ProductCreationRequest;
import com.springteam.computerstore.response.data.IdData;
import com.springteam.computerstore.response.data.ProductData;

import java.util.List;

public interface ProductService {
    IdData createProduct(ProductCreationRequest request);
    ProductData getProduct(Integer id);
    ProductData updateProductById(Integer id,ProductCreationRequest request);
    List<ProductData> getProductsByType(ProductType type);
}
