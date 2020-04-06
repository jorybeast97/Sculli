package com.magnoliaory.scullientityoperation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.magnoliaory.scullientityoperation.mapper")
public class SculliEntityOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SculliEntityOperationApplication.class, args);
    }

}
