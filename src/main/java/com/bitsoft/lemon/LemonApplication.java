package com.bitsoft.lemon;

import com.bitsoft.lemon.config.JdbcdConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Import(JdbcdConfig.class)
@PropertySource("classpath:jdbc.properties")
@MapperScan("com.bitsoft.lemon.mapper")
public class LemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonApplication.class, args);
	}

}
