package com.codepig.spy.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int userId;
    private String userName;
    private String userAddress;
    private String userSex;
    private Date userBirthday;
}
