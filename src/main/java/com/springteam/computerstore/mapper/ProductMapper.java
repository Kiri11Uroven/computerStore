package com.springteam.computerstore.mapper;

import com.springteam.computerstore.entity.ProductEntity;
import com.springteam.computerstore.request.ProductCreationRequest;
import com.springteam.computerstore.response.data.ProductData;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    /**
     * Преобразовать {@link ProductCreationRequest} в {@link ProductEntity}.
     *
     * @param source {@link ProductCreationRequest}
     * @return {@link ProductEntity}
     */
    ProductEntity toEntity(ProductCreationRequest source);

    /**
     * Преобразовываем сущность БД в ответ контроллера
     * @param entity сущность БД
     * @return ответ контроллера
     */
    ProductData toProductResponse(ProductEntity entity);
}
