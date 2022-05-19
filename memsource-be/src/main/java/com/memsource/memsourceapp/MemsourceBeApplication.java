package com.memsource.memsourceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MemsourceBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemsourceBeApplication.class, args);
    }

}
