package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping()
public class OtherController {
    private final UserService userService;

    public OtherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String open() {
        return "/index";
    }

    //// ДЛЯ РЕГЕСТРАЦИИ //////
    @GetMapping(value = "/registration")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.setUserForSave(user);
        return "redirect:/login";
    }
}
