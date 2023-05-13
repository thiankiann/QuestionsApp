package com.stormit.demo.patternsProject;

import com.stormit.patternsProject.SingletonExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class taskSingleton {


    private ApplicationContext ctx;

    @Test
    void shouldCreateOnlyOnceSingletonInstance() {
        SingletonExample instance1 = SingletonExample.getInstance();
        SingletonExample instance2 = SingletonExample.getInstance();

        assertThat(instance1 == instance2).isEqualTo(true);
    }

}
