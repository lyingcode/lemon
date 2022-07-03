package com.bitsoft.lemon.controller;

import com.bitsoft.lemon.model.demo.Hotel;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TestController {
    @GetMapping(value = "/getRec")
    public String getRec() {
        List<Hotel> hotelList = Collections.emptyList();
        if (!CollectionUtils.isEmpty(hotelList)) {
            return hotelList.toString();
        } else {
            return "no data";
        }
    }
}
