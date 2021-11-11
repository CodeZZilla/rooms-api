package com.example.roomsbotadmin.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private Date creationDate = new Date();

    private String type;

    private String name;
    private String lastName;

    private String nickname;

    private String role;
    private String typeSubscription;

    private List<Long> savedApartments;

    private boolean isConfirmed;
    private boolean isLocked;

    private String city;
    private List<String> region;
    private List<String> metroNames;

    private String phone;
    private String idTelegram;

    private long daysOfSubscription;
    private boolean isRent;

    private int priceMin;
    private int priceMax;

    private List<Integer> rooms;

    private List<Long> todayCompilation;
    private List<String> viewed;
    private String language;
    private int freeCounterSearch;


    private int userStatus;

}
