package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String adminPanel(Model model) {
        model.addAttribute("people", userService.getListUsers());
        return "adminPanel";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") long id, Role role) {
        model.addAttribute("user", userService.getIdForUser(id));
        model.addAttribute("role", role);
        return "edit";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String user(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getIdForUser(id));
        return "user";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        userService.setIdAndUserForEdit(id, user);
        return "redirect:/admin/panel";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") long id) {
        userService.setIdForDelete(id);
        return "redirect:/admin/panel";
    }
}
