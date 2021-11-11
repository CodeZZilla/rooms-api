package com.example.roomsbotadmin.controller;

import com.example.roomsbotadmin.services.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/user")
public class UserTableController {

    private final UserService userService;

    @GetMapping("/table")
    public String getUserTable(Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("userTelegram", userService.getAllUsers().get());

        return "user-table";
    }

    @GetMapping("/delete/{id}")
    @SneakyThrows
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        Thread.sleep(1000);
        return "redirect:/user/table";
    }

}
