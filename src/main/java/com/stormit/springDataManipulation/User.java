package com.stormit.springDataManipulation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Please insert the name")
    private String name;

    @Size(min = 3, max = 255)
    private String surname;


    @Min(value = 18, message = "You have to be an adult")
    private int age;

    @AssertTrue(message = "username is invalid")
    boolean isUsernameValid() {
        Set<String> prohibitedUsernames = Set.of("admin", "root");
        return username != null
                && !prohibitedUsernames.contains(username);
    }
}
