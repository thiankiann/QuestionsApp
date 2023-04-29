package com.stormit.demo.testProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notes")
public class NoteViewController {

    @GetMapping("add")
    public String addView() {
        return "note/add";
    }
}
