package com.stormit.demo.testProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private int id;
    private String name;

    public Note(String name) {
        this.id = 0;
        this.name = name;
    }
}
