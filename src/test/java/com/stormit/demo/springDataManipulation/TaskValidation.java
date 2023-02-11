package com.stormit.demo.springDataManipulation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TaskValidation {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldPassAllValidations() {
        // given
        User user = new User();
        user.setId(1);
        user.setUsername("stormit");
        user.setAge(39);
        user.setName("Mariusz");
        user.setSurname("Kacprzak");

        // when
        Throwable throwable = Assertions.catchThrowable(() -> userService.createUser(user));

        // then
        assertThat(throwable).isNull();
        assertThat(userRepository.findById(1)).isPresent();
    }

    @Test
    void shouldFailOnServiceValidation() {
        //given
        User user = new User();
        user.setId(1);

        //when
        Throwable throwable = Assertions.catchThrowable(() -> userService.createUser(user));

        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User.username can't be null");
    }

    @Test
    void shouldFailOnBeanValidation() {
        // given
        User user = new User();
        user.setId(1);
        user.setUsername("stormit");

        // when
        Throwable throwable = Assertions.catchThrowable(() -> userService.createUser(user));

        assertThat(throwable)
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("Error occurred: \nPlease insert the name\nYou have to be adult\n");
    }

    @Test
    void shouldFailOnProhibitedUsername() {
        // given
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setAge(30);
        user.setName("Tomek");

        // when
        Throwable throwable = Assertions.catchThrowable(() -> userService.createUser(user));

        assertThat(throwable)
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("Error occurred: \nUsername is invalid\n");
    }
}
