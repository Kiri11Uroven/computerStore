package com.springteam.computerstore.mapper;

import com.springteam.computerstore.config.MapStructConfig;
import com.springteam.computerstore.dto.ProductCreationRequest;
import com.springteam.computerstore.dto.ProductCreationResponse;
import com.springteam.computerstore.entity.Product;
import org.mapstruct.Mapper;


@Mapper(config = MapStructConfig.class)
public interface ProductMapper {
    /**
     * Преобразовать {@link ProductCreationRequest} в {@link Product}.
     *
     * @param source {@link ProductCreationRequest}
     * @return {@link Product}
     */

    Product toProduct(ProductCreationRequest source);

    /**
     * Преобразовать {@link Product} в {@link ProductCreationResponse}.
     *
     * @param source {@link Product}
     * @return {@link ProductCreationResponse}
     */
    ProductCreationResponse toProductCreationResponse(Product source);
}
