package com.springteam.computerstore.exception;

import com.springteam.computerstore.entity.ProductEntity;

public class CreationException extends AppException{
    public CreationException(ProductEntity entity) {
        super("Ошибка создания продукта с '%s'".formatted(entity));
    }
}
