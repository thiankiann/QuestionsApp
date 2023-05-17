package com.stormit.demo.patternsProject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class taskSingleton {


    @Autowired
    private ApplicationContext ctx;
    

    @Test
    void shouldCreateOnlyOnceSingletonInstance() {
        SingletonExample instance1 = SingletonExample.getInstance();
        SingletonExample instance2 = SingletonExample.getInstance();

        assertThat(instance1 == instance2).isEqualTo(true);
    }

    @Test
    void shouldCallSingletonBean() {
        // given
        ExampleService bean1 = ctx.getBean(ExampleService.class);
        ExampleService bean2 = ctx.getBean(ExampleService.class);

        String value = "StormIT";

        // when
        bean1.setValue(value);
        String result = bean2.getValue();

        // then
        assertThat(result.equals(value)).isEqualTo(true);
    }
}
