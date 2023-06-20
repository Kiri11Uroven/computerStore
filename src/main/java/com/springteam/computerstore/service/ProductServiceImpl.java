package com.springteam.computerstore.service;

import com.springteam.computerstore.common.InfoMessagesConstants;
import com.springteam.computerstore.common.ProductType;
import com.springteam.computerstore.entity.ProductEntity;
import com.springteam.computerstore.exception.CreationException;
import com.springteam.computerstore.exception.FindProductsByType;
import com.springteam.computerstore.exception.ProductNotFound;
import com.springteam.computerstore.exception.UpdateException;
import com.springteam.computerstore.mapper.ProductMapper;
import com.springteam.computerstore.repository.ProductRepository;
import com.springteam.computerstore.request.ProductCreationRequest;
import com.springteam.computerstore.response.data.IdData;
import com.springteam.computerstore.response.data.ProductData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;


    public IdData createProduct(ProductCreationRequest request) {
        log.debug(InfoMessagesConstants.CREATE_PRODUCT, request);

        IdData idData;
        ProductEntity entity = mapper.toEntity(request);
        Optional<ProductEntity> productEntity = repository.findBySerialNumber(entity.getSerialNumber());

        if (productEntity.isPresent()) {
            repository.updateAmount(entity.getSerialNumber(), entity.getAmount());
            idData = new IdData(productEntity.get().getId());
        } else {
            repository.save(entity);
            idData = new IdData(entity.getId());
        }

        repository.findById(entity.getId()).orElseThrow(() -> new CreationException(entity));

        log.debug(InfoMessagesConstants.PRODUCT_CREATED, idData);
        return idData;
    }

    public ProductData getProduct(Integer id) {
        log.debug(InfoMessagesConstants.GET_PRODUCT, id);

        ProductEntity productEntity = repository.findById(id).orElseThrow(() -> new ProductNotFound(id));
        ProductData productData = mapper.toProductResponse(productEntity);

        log.debug(InfoMessagesConstants.PRODUCT_RECEIVED, productData);
        return productData;
    }

    @Override
    public ProductData updateProductById(Integer id, ProductCreationRequest request) {

        log.debug(InfoMessagesConstants.PRODUCT_UPDATING, id, request);

        ProductEntity beforeUpdating = repository.findById(id).orElseThrow(() -> new ProductNotFound(id));

        ProductEntity product = mapper.toEntity(request);
        repository.updateProductById(id
            , product.getType().ordinal()
            , product.getSerialNumber()
            , product.getManufacturer()
            , product.getPrice()
            , product.getAmount()
            , product.getAdditionalProperty());

        ProductEntity afterUpdating = repository.findById(id).orElseThrow(() -> new ProductNotFound(id));

        if (beforeUpdating.equals(afterUpdating)) throw new UpdateException(beforeUpdating);

        ProductData productData = mapper.toProductResponse(product);


        log.debug(InfoMessagesConstants.PRODUCT_UPDATED, productData);
        return productData;
    }

    @Override
    public List<ProductData> getProductsByType(ProductType type) {
        log.debug(InfoMessagesConstants.GET_PRODUCTS_BY_TYPE, type);

        List<ProductEntity> productEntities = repository.findAllByType(type);

        if (productEntities.size() == 0) throw new FindProductsByType(type);

        List<ProductData> productDataResponse = productEntities
            .stream()
            .map(mapper::toProductResponse)
            .collect(Collectors.toList());

        log.debug(InfoMessagesConstants.PRODUCTS_BY_TYPE_RECEIVED, productDataResponse);
        return productDataResponse;
    }

}
