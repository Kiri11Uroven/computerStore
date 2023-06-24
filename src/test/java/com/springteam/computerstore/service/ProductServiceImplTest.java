package com.springteam.computerstore.service;

import com.springteam.computerstore.dto.request.ProductCreationRequest;
import com.springteam.computerstore.dto.response.data.ProductData;
import com.springteam.computerstore.entity.ProductEntity;
import com.springteam.computerstore.entity.ProductType;
import com.springteam.computerstore.exception.FindProductsByTypeException;
import com.springteam.computerstore.exception.ProductNotFoundException;
import com.springteam.computerstore.mapper.ProductMapper;
import com.springteam.computerstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository repository;
    @Spy
    ProductMapper mapper;
    @InjectMocks
    ProductServiceImpl service;
    ProductData productData;
    ProductData productData1;
    ProductEntity entity;
    ProductEntity entity1;
    List<ProductData> list;
    List<ProductEntity> entities;
    ProductCreationRequest request;

    @BeforeEach
    void init() {
        productData = new ProductData(1
            , "1"
            , "APPLE"
            , new BigDecimal("10")
            , 1
            , ProductType.LAPTOP
            , "13.0");
        productData1 = new ProductData(2
            , "2"
            , "SAMSUNG"
            , new BigDecimal("200")
            , 2, ProductType.LAPTOP
            , "14.0");
        entity = new ProductEntity(1
            , "1"
            , "APPLE"
            , new BigDecimal("10")
            , 1
            , ProductType.LAPTOP
            , "13.0");
        entity1 = new ProductEntity(2
            , "2"
            , "SAMSUNG"
            , new BigDecimal("200")
            , 2, ProductType.LAPTOP
            , "14.0");
        request = new ProductCreationRequest("1"
            , "APPLE"
            , new BigDecimal("10")
            , 1
            , ProductType.LAPTOP
            , "13.0");

        list = new ArrayList<>();
        list.add(productData);
        list.add(productData1);

        entities = new ArrayList<>();
        entities.add(entity);
        entities.add(entity1);

    }

    @Test
    @DisplayName("GET products/{type} Возвращает список объект ProductData по заданному типу")
    void getProductsByType() {

        doReturn(entities).when(repository).findAllByType(ProductType.LAPTOP);
        doReturn(productData).when(mapper).toProductResponse(entity);
        doReturn(productData1).when(mapper).toProductResponse(entity1);

        List<ProductData> data = service.getProductsByType(ProductType.LAPTOP);

        assertNotNull(data);
        assertEquals(2, data.size());
        assertEquals("APPLE", data.get(0).manufacturer());
        assertThrows(FindProductsByTypeException.class, () -> service.getProductsByType(ProductType.PC));
        assertThrows(FindProductsByTypeException.class, () -> service.getProductsByType(null));
    }

    @Test
    @DisplayName("GET products/{id} Возвращает объект ProductData")
    void getByIdTest() {
        doReturn(Optional.of(entity)).when(repository).findById(9);
        doReturn(productData).when(mapper).toProductResponse(entity);

        ProductData data = service.getProduct(9);

        assertNotNull(data);
        assertEquals("APPLE", data.manufacturer());
        assertThrows(ProductNotFoundException.class, () -> service.getProduct(10));
    }

    @Test
    void updateProductByIdTest() {
        doReturn(entity).when(mapper).toEntity(request);
        doReturn(productData).when(mapper).toProductResponse(entity);
        doReturn(Optional.of(entity)).when(repository).findById(1);
        service.updateProductById(1,request);
        assertThrows(ProductNotFoundException.class,()->service.updateProductById(2,request));
        verify(repository).updateProductById(1, 3, "1", "APPLE", new BigDecimal("10"), 1, "13.0");
    }

    @Test
    void createProduct() {
        doReturn(entity).when(mapper).toEntity(request);
        doReturn(Optional.of(entity)).when(repository).findBySerialNumber(request.serialNumber());
        service.createProduct(request);
        verify(repository).updateAmount(entity.getSerialNumber(),entity.getAmount());


    }
}
