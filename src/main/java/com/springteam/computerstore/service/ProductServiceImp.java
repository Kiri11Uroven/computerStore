package com.springteam.computerstore.service;

import com.springteam.computerstore.common.InfoMessages;
import com.springteam.computerstore.dto.ProductCreationRequest;
import com.springteam.computerstore.dto.ProductCreationResponse;
import com.springteam.computerstore.entity.Product;
import com.springteam.computerstore.entity.ProductType;
import com.springteam.computerstore.mapper.ProductMapper;
import com.springteam.computerstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductCreationResponse createProduct(ProductCreationRequest request) {

        log.info(InfoMessages.REQUEST_PRODUCT, request);

        Product product = mapper.toProduct(request);
        repository.save(product);

        log.info(InfoMessages.PRODUCT_CREATED, product);
        return mapper.toProductCreationResponse(product);
    }

    @Override
    public ProductCreationResponse getById(Long id) {
       Product product = repository.getProductById(id);
       log.info(InfoMessages.PRODUCT_CREATED);
      return mapper.toProductCreationResponse(product);
    }

    @Override
    public List<Product> getByType(ProductType type) {
        return null;
    }

    @Override
    public ProductCreationResponse update(Long id, ProductCreationRequest request) {
        return null;
    }


}
