package com.springteam.computerstore.entity;

import com.springteam.computerstore.common.ProductType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private static final String SEQUENCE_NAME = "products_id_seq";

    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Integer id;

    @Column(name = "serial", nullable = false, length = 64, unique = true)
    private String serialNumber;

    @Column(nullable = false, length = 64)
    private String manufacturer;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ProductType type;

    @Column(name = "properties", length = 64)
    private String additionalProperty;
}
