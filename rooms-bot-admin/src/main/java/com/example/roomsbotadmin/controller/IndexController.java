package com.example.roomsbotadmin.controller;

import com.example.roomsbotadmin.models.User;
import com.example.roomsbotadmin.services.UserService;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Controller
@AllArgsConstructor
@CrossOrigin
public class IndexController {

    private final UserService userService;

    @GetMapping(value = "/")
    public String getIndex(Model model) throws ExecutionException, InterruptedException {
        List<User> users = userService.getAllUsers().get();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int forDay = (int) users.stream().filter(x -> LocalDate.now().dayOfYear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).dayOfYear())).count();
        int forWeek = (int) users.stream().filter(x -> LocalDate.now().weekyear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).weekyear())).count();
        int forMonth = (int) users.stream().filter(x -> LocalDate.now().monthOfYear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).monthOfYear())).count();

        model.addAttribute("allUsers", users.size());
        model.addAttribute("forDay", forDay);
        model.addAttribute("forWeek", forWeek);
        model.addAttribute("forMonth", forMonth);

        return "index";
    }



}
