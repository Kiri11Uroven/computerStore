package com.springteam.computerstore.repository;

import com.springteam.computerstore.common.ProductType;
import com.springteam.computerstore.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findBySerialNumber(@NotNull String serialNumber);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE PRODUCTS set type = :type, serial = :serialNumber, manufacturer = :manufacturer, price = :price, amount = :amount, properties = :properties where id = :id")
    void updateProductById(Integer id, int type, String serialNumber, String manufacturer, BigDecimal price, Integer amount, String properties);

    List<ProductEntity> findAllByType(ProductType type);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE PRODUCTS set amount = amount + :amount where serial = :serial")
    void updateAmount(String serial, Integer amount);

    boolean existsByTypeAndAdditionalPropertyAndSerialNumber(ProductType type, String additionalProperty, String serialNumber);
}
