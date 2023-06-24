package com.springteam.computerstore.controller;

import com.springteam.computerstore.common.ApiPathConstants;
import com.springteam.computerstore.entity.ProductType;
import com.springteam.computerstore.dto.request.ProductCreationRequest;
import com.springteam.computerstore.dto.response.DataResponseApi;
import com.springteam.computerstore.dto.response.data.IdData;
import com.springteam.computerstore.dto.response.data.ProductData;
import com.springteam.computerstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController implements ProductsApi {
    private final ProductService service;

    @Override
    @PostMapping(ApiPathConstants.PRODUCT)
    public DataResponseApi<IdData> addProduct(@RequestBody ProductCreationRequest creationRequest) {
        return new DataResponseApi<>(service.createProduct(creationRequest));
    }

    @Override
    @GetMapping(ApiPathConstants.PRODUCT_ID)
    public DataResponseApi<ProductData> getById(@PathVariable Integer id) {
        return new DataResponseApi<>(service.getProduct(id));
    }

    @Override
    @PutMapping(ApiPathConstants.PRODUCT_ID)
    public DataResponseApi<ProductData> updateProduct(@PathVariable Integer id,@RequestBody ProductCreationRequest request) {
        return new DataResponseApi<>(service.updateProductById(id, request));
    }

    @Override
    @GetMapping(ApiPathConstants.PRODUCT_TYPE)
    public DataResponseApi<List<ProductData>> getByType(@PathVariable ProductType type) {
        return new DataResponseApi<>(service.getProductsByType(type));
    }
}
