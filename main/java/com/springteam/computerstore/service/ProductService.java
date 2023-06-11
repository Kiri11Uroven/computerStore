package com.springteam.computerstore.service;

import com.springteam.computerstore.dto.ProductCreationRequest;
import com.springteam.computerstore.dto.ProductCreationResponse;
import com.springteam.computerstore.entity.Product;
import com.springteam.computerstore.entity.ProductType;

import java.util.List;


public interface ProductService {

   ProductCreationResponse createProduct(ProductCreationRequest request);
   ProductCreationResponse getById(Long id);
   List<Product> getByType(ProductType type);
   ProductCreationResponse update(Long id, ProductCreationRequest request);

}
