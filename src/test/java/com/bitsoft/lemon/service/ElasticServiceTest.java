package com.bitsoft.lemon.service;

import com.bitsoft.lemon.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElasticServiceTest {
    @Autowired
    private ElasticService elasticService;

    @Test
    void simplePrintProductInfo() {
        elasticService.simplePrintProductInfo();
    }

    @Test
    void createIndex() {
        elasticService.createIndex();
    }

    @Test
    void saveUseIndex() {
        elasticService.saveUseIndex();
    }

    @Test
    void findById() {
        Hotel hotel = elasticService.findById();
//        Assertions.assertNotNull(hotel);
        System.out.println(hotel);
    }
}