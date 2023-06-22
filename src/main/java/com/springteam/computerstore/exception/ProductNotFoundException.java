package com.springteam.computerstore.exception;

public class ProductNotFoundException extends AppException {
    public ProductNotFoundException(int id) {
        super("Продукта с ID '%d' не найдено".formatted(id));
    }
}
