package com.example.roomsbotadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RoomsBotAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomsBotAdminApplication.class, args);
    }

}
