package com.stormit.demo.testProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskSpringBootTest {

    @Autowired
    private ExampleService exampleService;

    @Test
    void simpleBeanInjection() {
        // given
        String input = "abc";

        // when
        String result = exampleService.doSomeLogic(input);

        // then
        assertThat(result).isEqualTo("ABC");
    }
}
