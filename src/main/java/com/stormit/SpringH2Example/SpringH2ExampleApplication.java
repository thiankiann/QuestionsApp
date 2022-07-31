package com.stormit.SpringH2Example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringH2ExampleApplication {

    @GetMapping("test")
    String test() {
        return "" + System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringH2ExampleApplication.class, args);
    }
}
