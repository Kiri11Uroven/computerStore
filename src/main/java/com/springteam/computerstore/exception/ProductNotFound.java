package com.springteam.computerstore.exception;

public class ProductNotFound extends AppException {
    public ProductNotFound(int id) {
        super("Продукта с ID '%d' не найдено".formatted(id));
    }
}
