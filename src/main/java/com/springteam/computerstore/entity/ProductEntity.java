package com.springteam.computerstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 1)
    private String serialNumber;

    @Column(nullable = false, length = 64)
    @Length(min = 1)
    private String manufacturer;

    @Column(nullable = false)
    @DecimalMin(message = "Price should be positive",
        value = "0")
    private BigDecimal price;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ProductType type;

    @Column(name = "properties", length = 64)
    private String additionalProperty;

}
