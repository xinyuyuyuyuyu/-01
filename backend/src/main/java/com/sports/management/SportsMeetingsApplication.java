package com.sports.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sports.management.mapper")
public class SportsMeetingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsMeetingsApplication.class, args);
    }

}
