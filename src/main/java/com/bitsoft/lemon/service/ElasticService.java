package com.bitsoft.lemon.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.bitsoft.lemon.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticService {
    @Autowired
    private ElasticsearchClient client;

    public void simplePrintProductInfo() throws IOException {
        SearchResponse<Product> search = client.search(s -> s.index("products").query(q -> q.term(t -> t.field("name").value(v -> v.stringValue("bicycle")))), Product.class);

        for (Hit<Product> hit : search.hits().hits()) {
            System.out.println(hit.source());
        }
    }

    public void createIndex() throws IOException {
        CreateIndexResponse createIndexResponse = client.indices().create(new CreateIndexRequest.Builder().index("my-index")
                .aliases("foo", new Alias.Builder().isWriteIndex(true).build())
                .build()
        );
        System.out.println(createIndexResponse);
    }
}
