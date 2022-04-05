package com.bitsoft.lemon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ElasticServiceTest {
    @Autowired
    private ElasticService elasticService;
    @Test
    void simplePrintProductInfo() throws IOException {
        elasticService.simplePrintProductInfo();
    }

    @Test
    void createIndex() throws IOException {
        elasticService.createIndex();
    }
}