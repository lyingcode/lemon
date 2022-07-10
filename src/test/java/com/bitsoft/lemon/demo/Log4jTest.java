package com.bitsoft.lemon.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class Log4jTest {
    @Test
    public void logTest() {
        for(int i=0;i< 1000000;i++){
            log.info("hello,world,rowNum:{}",i);
        }
        String row;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("/Users/jameszhang/logs/lemon.log"))) {
            while ((row = bufferedReader.readLine()) != null) {
                System.out.println(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
