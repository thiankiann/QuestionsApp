package com.stormit.demo.springDataManipulation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("users", userService.findAll());

        return "user/index";
    }

    @GetMapping("add")
    public String addView(Model model){
        User user = new User();
        user.setName("-name-");
        user.setSurname("-surname-");
        user.setUsername("-username-");

        model.addAttribute("user", user);

        return "user/add";
    }

    @PostMapping("add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/add";
        }

        userService.createUser(user);

        return "redirect:/users";
    }
}
