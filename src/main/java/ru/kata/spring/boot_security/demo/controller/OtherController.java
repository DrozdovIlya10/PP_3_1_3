package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping()
public class OtherController {
    private final UserService userService;

    public OtherController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String open() {
        return "/index";
    }

    //// ДЛЯ РЕГЕСТРАЦИИ //////
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute("user") User user) {
        userService.setUserForSave(user);
        return "redirect:/login";
    }
}
