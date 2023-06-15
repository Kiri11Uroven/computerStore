package com.springteam.computerstore.controller;

import com.springteam.computerstore.common.ApiPathConstrants;
import com.springteam.computerstore.common.ProductType;
import com.springteam.computerstore.request.ProductCreationRequest;
import com.springteam.computerstore.response.DataResponseApi;
import com.springteam.computerstore.response.data.IdData;
import com.springteam.computerstore.response.data.ProductData;
import com.springteam.computerstore.service.ProductServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class ProductController implements ProductsApi{
    private final ProductServiceImpl service;

    @PostMapping(ApiPathConstrants.PRODUCT)
    public DataResponseApi<IdData> addProduct(@Valid @RequestBody ProductCreationRequest creationRequest) {
        return new DataResponseApi<>(service.createProduct(creationRequest));
    }

    @GetMapping(ApiPathConstrants.PRODUCT_ID)
    public DataResponseApi<ProductData> getById(@Min(1) @PathVariable Integer id) {
        return new DataResponseApi<>(service.getProduct(id));
    }

    @Override
    public DataResponseApi<ProductData> updateProduct(ProductCreationRequest request) {
        return null;
    }

    @Override
    public DataResponseApi<List<ProductData>> getByType(ProductType type) {
        return null;
    }
}
