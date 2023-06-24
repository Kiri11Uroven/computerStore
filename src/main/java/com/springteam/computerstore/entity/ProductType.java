package com.springteam.computerstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

//
// !!! ПОРЯДОК НЕ МЕНЯТЬ !!!
//
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProductType {
    HDD,
    MONITOR,
    PC,
    LAPTOP;

    @JsonValue
    public String jsonValue() {
        return this.name();
    }
}
