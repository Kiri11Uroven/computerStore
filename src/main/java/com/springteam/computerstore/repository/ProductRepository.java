package com.springteam.computerstore.repository;

import com.springteam.computerstore.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findBySerialNumber(@NotNull String serialNumber);
}
