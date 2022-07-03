package com.bitsoft.lemon.demo;

import com.bitsoft.lemon.junit.MyArgumentsProvider;
import com.bitsoft.lemon.junit.PersonAggregator;
import com.bitsoft.lemon.model.demo.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

@Slf4j
public class ArgumentTest {
    @Order(15)
    @DisplayName("ArgumentsProvider接口的实现类提供的数据作为入参")
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void argumentsSourceTest(String candidate){
      log.info("argumentsSourceTest[{}]",candidate);
    }

    @Order(16)
    @DisplayName("int型子都给你转为double型入参")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void argumentConversionTest(double candidate){
        log.info("argumentConversionTest[{}]",candidate);
    }

    @Order(17)
    @DisplayName("string型,指定转换器,转为LocalDate型入参")
    @ParameterizedTest
    @ValueSource(strings = {"01.01.2017","31.12.2017"})
    void argumentConversionWithConvertTest(@JavaTimeConversionPattern("dd.MM.yyyy")LocalDate candidate){
        log.info("argumentConversionWithConverterTest[{}]",candidate);
    }

    @Order(12)
    @DisplayName("CSV格式多条记录入参")
    @ParameterizedTest
    @CsvSource({
            "apple1,11",
            "banana1,12",
            "'lemon1,lime1',10"
    })
    void csvSourceTest(String fruit,int rank){
        log.info("csvSourceTest,fruit[{}],rank[{}]",fruit,rank);
    }

    @Order(18)
    @DisplayName("CsvSource的多个字段聚合到ArgumentsAccessor实例")
    @ParameterizedTest
    @CsvSource({
            "Jane1,Doe1,BIG",
            "John1,Doe1,SMALL"
    })
    void argumentAccessorTest(@AggregateWith(PersonAggregator.class) Person person){
        log.info("argumentsAccessTest[{}]",person);
    }
}
