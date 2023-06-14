package com.springteam.computerstore.controller;

import com.springteam.computerstore.common.ApiPathConstrants;
import com.springteam.computerstore.dto.ProductCreationRequest;
import com.springteam.computerstore.dto.ProductCreationResponse;
import com.springteam.computerstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstrants.PRODUCT)
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductCreationResponse> addProduct(@RequestBody ProductCreationRequest creationRequest) {
        return ResponseEntity.ok(service.createProduct(creationRequest));
    }

    @GetMapping(ApiPathConstrants.PRODUCT_ID)
    public ResponseEntity<ProductCreationResponse> getById(@PathVariable Long id){
       return ResponseEntity.ok(service.getById(id));
    }


}
