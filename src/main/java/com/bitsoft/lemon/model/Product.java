package com.bitsoft.lemon.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private BigDecimal price;
    private Boolean available;
    private String productId;
    private String name;
}
