package com.bitsoft.lemon.utils;

import org.springframework.context.ApplicationContext;

public class SpringUtils {
    private SpringUtils(){}
    private static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringUtils.applicationContext = applicationContext;
    }
    public static ApplicationContext getContext() {
        return SpringUtils.applicationContext;
    }
}
