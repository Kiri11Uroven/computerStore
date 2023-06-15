package com.springteam.computerstore.service;

import com.springteam.computerstore.common.ProductType;
import com.springteam.computerstore.entity.ProductEntity;
import com.springteam.computerstore.exception.ProductNotFound;
import com.springteam.computerstore.mapper.ProductMapper;
import com.springteam.computerstore.repository.ProductRepository;
import com.springteam.computerstore.request.ProductCreationRequest;
import com.springteam.computerstore.response.data.IdData;
import com.springteam.computerstore.response.data.ProductData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public IdData createProduct(ProductCreationRequest request) {
        log.debug("entry createProduct(..); {}", request);
        IdData idData;

        Optional<ProductEntity> optProductEntity = repository.findBySerialNumber(request.serialNumber());
        if (optProductEntity.isPresent()) {
            optProductEntity.get().setAmount(request.amount());
            repository.save(optProductEntity.get());
            idData = new IdData(optProductEntity.get().getId());
        } else {
            ProductEntity productEntity = mapper.toEntity(request);
            productEntity = repository.save(productEntity);
            idData = new IdData(productEntity.getId());
        }

        log.debug("exit createProduct(..): {}", idData);
        return idData;
    }

    public ProductData getProduct(Integer id) {
        log.debug("entry getProduct(..); {}", id);

        ProductEntity productEntity = repository.findById(id).orElseThrow(() -> new ProductNotFound(id));
        ProductData productData = mapper.toProductResponse(productEntity);

        log.debug("exit getProduct(..): {}", productData);
        return productData;
    }

    @Override
    public ProductData updateProductById(Integer id, ProductCreationRequest request) {
        return null;
    }

    @Override
    public List<ProductData> getProductsByType(ProductType type) {
        return new ArrayList<>();
    }
}
