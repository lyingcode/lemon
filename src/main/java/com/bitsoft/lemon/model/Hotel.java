package com.bitsoft.lemon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Hotel {
    @Id
    private String id;
    private String title;
    private String city;
    private String price;
}
