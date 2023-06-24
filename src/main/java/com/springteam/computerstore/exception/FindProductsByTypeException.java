package com.springteam.computerstore.exception;

import com.springteam.computerstore.entity.ProductType;

public class FindProductsByTypeException extends AppException{
    public FindProductsByTypeException(ProductType types) {
        super("Продукта с типом '%s' не найдено".formatted(types));
    }
}
