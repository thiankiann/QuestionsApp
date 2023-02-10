package com.stormit.demo.springDataManipulation;

import lombok.Data;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLombok {

    @Test
    void boilerPlateCodeExample() {
        class User {
            private String username;
            private String name;
            private String surname;
            private int age;

            public User() {
            }

            public User(String name) {
                this.name = name;
            }

            public User(String username, String name, String surname, int age) {
                this.username = username;
                this.name = name;
                this.surname = surname;
                this.age = age;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSurname() {
                return surname;
            }

            public void setSurname(String surname) {
                this.surname = surname;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                User user = (User) o;
                return age == user.age && Objects.equals(username, user.username)
                        && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
            }

            @Override
            public int hashCode() {
                return Objects.hash(username, name, surname, age);
            }

            @Override
            public String toString() {
                return "User{" +
                        "username='" + username + '\'' +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", age=" + age +
                        '}';
            }
        }

        User user = new User("Tomek");

        assertEquals("Tomek", user.getName());
        assertEquals("User{username='null', name='Tomek', surname='null', age=0}", user.toString());
    }

    @Test
    void shouldGenerateGetters() {
        class User1 {

            @Getter
            private String name;

            private String surname;
        }

        @Getter
        class User2 {

            private String name;

            private String surname;
        }

        User1 user1 = new User1();
        User2 user2 = new User2();

        assertEquals(null, user1.getName());

        assertEquals(null, user2.getName());
        assertEquals(null, user2.getSurname());
    }

    @Test
    void shouldGenerateCodeFromDataAnnotation() {
        // @Data ==> @Getter, @Setter, @EqualsAndHashCode, @RequiredArgsConstructor and @ToString
        @Data
        class User {
            private final String name;
            private String surname;
        }

        User user = new User("Tomek");
        user.setSurname(null);

        assertEquals("Tomek", user.getName());
        assertEquals(null, user.getSurname());
        assertEquals("User(name=Tomek, surname=null)", user.toString());
        assertEquals(true, user.equals(new User("Tomek")));
    }
}

