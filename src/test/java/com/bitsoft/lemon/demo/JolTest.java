package com.bitsoft.lemon.demo;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class JolTest {
    @Test
    public void jolObjectTest(){
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        Integer integer = 10;
        System.out.println(ClassLayout.parseInstance(integer).toPrintable());
    }
}
