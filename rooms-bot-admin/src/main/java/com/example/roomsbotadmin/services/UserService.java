package com.example.roomsbotadmin.services;

import com.example.roomsbotadmin.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private static final String baseUrl = "http://rooms-bot-api:8080";

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<List<User>> getAllUsers() {
        User[] userArray = Objects.requireNonNull(restTemplate.getForObject(baseUrl + "/api/user", User[].class));
        List<User> users = Arrays.stream(userArray).collect(Collectors.toList());

        return CompletableFuture.completedFuture(users);
    }

    @Async
    public void deleteUser(String id) {
        restTemplate.delete(baseUrl + "/api/user/delete/" + id);
    }
}
