package com.bitsoft.lemon.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@Document(indexName = "products")
public class Product {
    private BigDecimal price;
    private Boolean available;
    private String productId;
    private String name;
}
