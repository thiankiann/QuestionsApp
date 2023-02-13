package com.stormit.demo.springDataManipulation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Please insert the name")
    private String name;

    @NotBlank
    private String content;
}
