package com.stormit.demo.testProject;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/notes")
public class NoteApiController {

    @GetMapping
    public List<Note> getNotes() {
        return Arrays.asList(new Note("Note1"), new Note("Note2"));
    }

    @GetMapping("{id}")
    public Note getNote(@PathVariable int id) {
        return new Note(id, "Note-" + id);
    }

    @PutMapping("{id}")
    public Note updateNote( @PathVariable int id, @RequestBody Note note){
        return note;
    }
}
