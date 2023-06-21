package com.springteam.computerstore.entity;

import com.springteam.computerstore.common.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity that)) return false;
        return getAmount() == that.getAmount()
            && getId().equals(that.getId())
            && getSerialNumber().equals(that.getSerialNumber())
            && getManufacturer().equals(that.getManufacturer())
            && getPrice().equals(that.getPrice()) && getType() == that.getType()
            && Objects.equals(getAdditionalProperty(), that.getAdditionalProperty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
            getSerialNumber(),
            getManufacturer(),
            getPrice(), getAmount(),
            getType(), getAdditionalProperty());
    }
}
