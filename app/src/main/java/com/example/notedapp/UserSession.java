package com.example.notedapp;

public class UserSession {

    private static int userId ;

    public UserSession(int userId) {
        this.userId = userId;
    }

    public static int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
