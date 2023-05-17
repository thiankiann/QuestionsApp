package com.stormit.demo.patternsProject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    private String name;
    private String surname;
    private int age;
    private UserType userType;
    private Address address;

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
        this.userType = builder.userType;
        this.address = builder.address;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    @Setter
    @Getter
    public static class UserBuilder {

        private String name;
        private String surname;
        private int age;
        private UserType userType;
        private Address address;

        private UserBuilder() {
            // some default values
        }

        public static UserBuilder youngInternal() {
            return new UserBuilder()
                    .withUserType(UserType.INTERNAL)
                    .withAge(13);
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder withUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public UserBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

