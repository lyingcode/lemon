package com.bitsoft.lemon.model.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@Document(indexName = "products")
public class Product {
    @Id
    private String id;
    private BigDecimal price;
    private Boolean available;
    private String productId;
    private String name;
}
