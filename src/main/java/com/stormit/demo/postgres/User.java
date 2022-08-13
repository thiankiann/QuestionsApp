package com.stormit.demo.postgres;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;

public class User {
    @Id
    private int id;

    @Column(name = "user_name")
    private String username;
}
