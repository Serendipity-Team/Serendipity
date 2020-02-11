package com.serendipity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gemini
 */
@SpringBootApplication
@MapperScan(basePackages = "com.serendipity.mapper")
public class SerendipityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SerendipityApplication.class, args);
    }

}
