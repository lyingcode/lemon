package com.bitsoft.lemon.service;

import com.bitsoft.lemon.model.demo.Hotel;
import com.bitsoft.lemon.model.demo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
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
        product.setId("2");
        product.setProductId("EFGH");
        product.setAvailable(false);
        product.setPrice(BigDecimal.valueOf(300.15));
        product.setName("服装");
        elasticsearchOperations.save(product);
    }

    public void saveUseIndex() {
        Hotel hotel = new Hotel();
        hotel.setPrice("258.00");
        hotel.setCity("深圳");
        hotel.setTitle("新华宾馆");
        hotel.setId("1");
        //indexQuery最终生效的是build里的 id和IndexCoordinates的indexName
        IndexQuery indexQuery = new IndexQueryBuilder().withId("3").withObject(hotel).build();
        elasticsearchOperations.index(indexQuery, IndexCoordinates.of("new_hotels"));
    }

    public Hotel findById() {
        return elasticsearchOperations.get("1", Hotel.class, IndexCoordinates.of("new_hotels"));
    }
}
