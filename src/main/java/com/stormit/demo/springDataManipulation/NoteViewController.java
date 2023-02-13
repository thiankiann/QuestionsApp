package com.stormit.demo.springDataManipulation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("notes")
@RequiredArgsConstructor
public class NoteViewController {

    private final NoteService noteService;

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("notes", noteService.findAll());

        return "note/index";
    }

    @GetMapping("add")
    public String addView(Model model){
        Note note = new Note();
        note.setName("-name-");
        note.setContent("-content-");

        model.addAttribute("note", note);
        model.addAttribute("message", "Add new Note");

        return "note/add";
    }

    @PostMapping("add")
    public String addNote(@Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Fix errors");
            return "note/add";
        }

        model.addAttribute("message", "New note added");
        noteService.createNote(note);

        return "note/add";
    }

    @GetMapping("add-prg")
    public String addPRGView(Model model){
        Note note = new Note();
        note.setName("-name-");
        note.setContent("-content-");

        model.addAttribute("note", note);
        model.addAttribute("message", "Add new Note");

        return "note/add-prg";
    }

    @PostMapping("add-prg")
    public String addPRG(@Valid Note note, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("error", "Fix errors");
            return "note/add-prg";
        }

        ra.addFlashAttribute("success", "New note added");

        noteService.createNote(note);

        return "redirect:/notes";
    }
}

