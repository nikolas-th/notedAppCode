package com.example.notedapp;

public class UserSession {

    private static int userId ;

    public UserSession(int userId) {
        UserSession.userId = userId;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        UserSession.userId = userId;
    }

}
