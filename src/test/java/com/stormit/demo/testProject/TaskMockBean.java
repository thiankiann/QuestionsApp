package com.stormit.demo.testProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskMockBean {

    @Autowired
    private ExampleService exampleService;

    @MockBean
    private ExampleBean exampleBean;

    @Test
    void simpleBeanInjection() {
        // given
        String input = "abc";

        when(exampleBean.doSomeLogic(anyString()))
                .thenReturn("mocked value");

        // when
        String result = exampleService.doSomeLogic(input);

        // then
        assertThat(result).isEqualTo("mocked value");
    }
}
