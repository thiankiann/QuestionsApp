package com.stormit.demo.springDataManipulation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Please insert the username")
    private String username;

    @NotNull(message = "Please insert the name")
    private String name;

    @Size(min = 3, max = 255)
    private String surname;

    @Min(value = 18, message = "You have to be adult")
    private int age;

    @AssertTrue(message = "Username is invalid")
    boolean isUsernameValid() {
        Set<String> prohibitedUsernames = Set.of("admin", "root");
        return username != null
                && !prohibitedUsernames.contains(username);
    }
}
