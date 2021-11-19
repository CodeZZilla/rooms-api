package com.example.roomsbotapi.controllers;

import com.example.roomsbotapi.models.User;
import com.example.roomsbotapi.services.UserService;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final UserService userService;

    @GetMapping("/dateStatistic")
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

    @GetMapping("/stagesAtWhichClientsStopped")
    public Map<String, Integer> stagesAtWhichClientsStopped() {
        List<User> users = userService.findAll();
        Map<String, Integer> mapStages = new HashMap<>();

        mapStages.put("firstStage", (int) users.stream().map(User::getUserStatus).filter(user -> user == 1).count());
        mapStages.put("secondStage", (int) users.stream().map(User::getUserStatus).filter(user -> user == 2).count());
        mapStages.put("thirdStage", (int) users.stream().map(User::getUserStatus).filter(user -> user == 3).count());
        mapStages.put("fourthStage", (int) users.stream().map(User::getUserStatus).filter(user -> user == 4).count());
        mapStages.put("fifthStage", (int) users.stream().map(User::getUserStatus).filter(user -> user == 5).count());
        mapStages.put("sixthStage", (int) users.stream().map(User::getUserStatus).filter(user -> user == 6).count());
        mapStages.put("seventhStage", (int) users.stream().map(User::getUserStatus).filter(user -> user == 7).count());

        return mapStages;
    }

    @GetMapping("/dataForChats")
    public Map<String, Object> dataForChats() {
        List<User> users = userService.findAll();
        Map<String, Object> mapData = new HashMap<>();

        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");


        Set<java.time.LocalDateTime> dates = users.stream().map(x -> java.time.LocalDateTime.parse(format.format(x.getCreationDate())))
                .collect(Collectors.toSet());

        mapData.put("usersCount", users.size());
        mapData.put("dates", dates);

        System.out.println(dates);

        return mapData;
    }
}
