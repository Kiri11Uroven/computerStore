package com.springteam.computerstore.exception;

import com.springteam.computerstore.common.ProductType;

public class FindProductsByType extends AppException{
    public FindProductsByType(ProductType types) {
        super("Продукты с типом '%s' не найдено".formatted(types));
    }
}
