package com.bitsoft.lemon.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class LocalTimeTest {
    @Test
    public void localTimeTest() {
        LocalTime localTime = LocalTime.now();
        log.info("localTime:{}", localTime);
        int nano = localTime.getNano();
        int second = localTime.getSecond();
        log.info("nano:{},second:{}", nano, second);
        long fieldValue = localTime.getLong(ChronoField.CLOCK_HOUR_OF_DAY);
        log.info("fieldValue:{}", fieldValue);
    }

    @Test
    public void localDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("before truncate:{}", localDateTime);
        LocalDateTime newTime = localDateTime.truncatedTo(ChronoUnit.DAYS);
        log.info("after truncate:{}", newTime);
        //TemporalAdjusters 内置方法很好用
        newTime = localDateTime.with(TemporalAdjusters.firstDayOfNextMonth()).truncatedTo(ChronoUnit.DAYS);
        log.info("newTime:{}",newTime);
    }
}
