package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/panel")
    public String adminPanel(Model model) {
        model.addAttribute("people", userService.getListUsers());
        return "adminPanel";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id, Role role) {
        model.addAttribute("user", userService.getIdForUser(id));
        model.addAttribute("listRoles", roleService.getListRoles());
        return "edit";
    }

    @GetMapping(value = "/user/{id}")
    public String user(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getIdForUser(id));
        return "user";
    }

    @PostMapping(value = "/edit/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("listRoles") ArrayList<Long> roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.setUserForEdit(user);
        return "redirect:/admin/panel";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.setIdForDelete(id);
        return "redirect:/admin/panel";
    }
}
