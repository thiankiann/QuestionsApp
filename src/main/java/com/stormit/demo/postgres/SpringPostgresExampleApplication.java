package com.stormit.demo.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringPostgresExampleApplication {
    @GetMapping("time")
    String time(){
        return ""+System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPostgresExampleApplication.class, args);
    }

}
