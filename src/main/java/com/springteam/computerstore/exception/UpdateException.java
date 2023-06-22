package com.springteam.computerstore.exception;

import com.springteam.computerstore.entity.ProductEntity;

public class UpdateException extends AppException{
    public UpdateException(ProductEntity updatingProduct) {
        super("Ошибка обновления '%s'".formatted(updatingProduct));
    }
}
