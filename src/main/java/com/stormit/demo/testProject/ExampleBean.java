package com.stormit.demo.testProject;

import org.springframework.stereotype.Component;

@Component
public class ExampleBean {
    public String doSomeLogic(String txt){
        return txt.toUpperCase();
    }
}
