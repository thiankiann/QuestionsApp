package com.stormit.demo.patternsProject;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

