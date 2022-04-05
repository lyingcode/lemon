package com.bitsoft.lemon.service;

import com.bitsoft.lemon.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ElasticService {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void simplePrintProductInfo() {

    }

    public void createIndex() {
        Product product = new Product();
        product.setProductId("ABCDEF");
        product.setAvailable(false);
        product.setPrice(BigDecimal.valueOf(300.15));
        product.setName("服装");
        elasticsearchOperations.save(product);
    }
}
