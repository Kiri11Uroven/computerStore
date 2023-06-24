package com.springteam.computerstore.controller;

import com.springteam.computerstore.dto.request.ProductCreationRequest;
import com.springteam.computerstore.dto.response.DataResponseApi;
import com.springteam.computerstore.dto.response.data.IdData;
import com.springteam.computerstore.dto.response.data.ProductData;
import com.springteam.computerstore.entity.ProductType;
import com.springteam.computerstore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    ProductService service;
    @InjectMocks
    ProductController controller;
    IdData data = new IdData(1);
    ProductCreationRequest request = new ProductCreationRequest("1"
        , "APPLE"
        , new BigDecimal("10")
        , 1
        , ProductType.LAPTOP
        , "13.0");
    ProductData product = new ProductData(1
        , "1"
        , "APPLE"
        , new BigDecimal("10")
        , 1
        , ProductType.LAPTOP
        , "13.0");
    ProductData product1 = new ProductData(2
        , "2"
        , "APPLE"
        , new BigDecimal("11")
        , 1
        , ProductType.LAPTOP
        , "15.0");

    @Test
    void addProduct() {
        Mockito.doReturn(data).when(service).createProduct(request);

        DataResponseApi<IdData> productData = controller.addProduct(request);

        assertNotNull(productData.data());
    }

    @Test
    void getById() {

        Mockito.doReturn(product).when(service).getProduct(1);

        var productData = controller.getById(1);

        assertNotNull(productData.data());
        assertEquals(product.manufacturer(),productData.data().manufacturer());
    }

    @Test
    void updateProduct() {
        Mockito.doReturn(product).when(service).updateProductById(1,request);
        var productData = controller.updateProduct(1,request);
        assertNotNull(productData.data());
    }

    @Test
    void getByType() {
        List <ProductData> list = Arrays.asList(product,product1);
        Mockito.doReturn(list).when(service).getProductsByType(ProductType.LAPTOP);
        var productData = controller.getByType(ProductType.LAPTOP);
        assertNotNull(productData.data());
    }
}
