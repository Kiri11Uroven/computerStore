package com.springteam.computerstore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private ProductType type;
    private String seriesNumber;
    private String manufacturer;
    private BigDecimal price;
    private int amount;
    private String additionalProperty;

}
