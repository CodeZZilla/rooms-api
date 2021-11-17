package com.example.roomsbotapi.controllers;

import com.example.roomsbotapi.models.User;
import com.example.roomsbotapi.services.UserService;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final UserService userService;

    @GetMapping("/dateStatistic")
    @ResponseBody
    public Map<String, Integer> getDateStatistic() {
        Map<String, Integer> integerMap = new HashMap<>();
        List<User> users = userService.findAll();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int forDay = (int) users.stream().filter(x -> LocalDate.now().dayOfYear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).dayOfYear())).count();
        int forWeek = (int) users.stream().filter(x -> LocalDate.now().weekyear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).weekyear())).count();
        int forMonth = (int) users.stream().filter(x -> LocalDate.now().monthOfYear().equals(LocalDate.parse(dateFormat.format(x.getCreationDate())).monthOfYear())).count();

        integerMap.put("allUsers", users.size());
        integerMap.put("forDay", forDay);
        integerMap.put("forWeek", forWeek);
        integerMap.put("forMonth", forMonth);

        return integerMap;
    }
}
