package com.stormit.demo.patternsProject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class taskBuilder {

    @Test
    void shouldCreateNewObjectFromBuilder() {
        User user = User.builder()
                .withName("Mariusz")
                .withSurname("Kacprzak")
                .withAge(40)
                .withUserType(UserType.INTERNAL)
                .build();
        assertThat(user.getName()).isEqualTo("Mariusz");
        assertThat(user.getAge()).isEqualTo(40);
    }

    @Test
    void shouldCreatePredefinedObjectFromBuilder() {
        User user = User.builder()
                .youngInternal()
                .withName("Marek")
                .build();

        assertThat(user.getName()).isEqualTo("Marek");
        assertThat(user.getAge()).isEqualTo(13);
        assertThat(user.getUserType()).isEqualTo(UserType.INTERNAL);
    }

    @Test
    void shouldCreateObjectWithLombokBuilder() {
        UserWithLombokBuilder user = UserWithLombokBuilder.builder()
                .name("Mariusz")
                .surname("Kacprzak")
                .age(40)
                .userType(UserType.INTERNAL)
                .build();
        assertThat(user.getName()).isEqualTo("Mariusz");
        assertThat(user.getAge()).isEqualTo(40);
        assertThat(user.getAge()).isEqualTo(UserType.INTERNAL);
    }
}
