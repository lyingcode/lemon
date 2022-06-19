package com.bitsoft.lemon.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestWordDealUtilWithParam {
    private String expected;
    private String target;


    public static Collection words(){
        return Arrays.asList("hello","world");
    }
    public TestWordDealUtilWithParam(String expected,String target){
        this.expected = expected;
        this.target = target;
    }
}
