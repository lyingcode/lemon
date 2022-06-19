package com.bitsoft.lemon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "hotels")
public class Hotel {
    @Id
    private String id;
    private String title;
    private String city;
    private String price;
}
