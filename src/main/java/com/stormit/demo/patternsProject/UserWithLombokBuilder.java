package com.stormit.demo.patternsProject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserWithLombokBuilder {

    private String name;
    private String surname;
    private int age;
    private UserType userType;
}
