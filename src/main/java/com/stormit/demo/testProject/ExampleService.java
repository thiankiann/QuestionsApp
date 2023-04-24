package com.stormit.demo.testProject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleBean exampleBean;

    public String doSomeLogic(String txt){
        return exampleBean.doSomeLogic(txt);
    }
}
