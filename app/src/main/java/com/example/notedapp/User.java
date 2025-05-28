package com.example.notedapp;

public class User {

    private  int  id;
    private String type;
    private String rank;
    private String username;
    private String password;
    private int reviewCounter;

    public User(int id, String type, String rank, String username, String password, int reviewCounter) {
        this.id = id;
        this.type = type;
        this.rank = rank;
        this.username = username;
        this.password = password;
        this.reviewCounter = reviewCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReviewCounter() {
        return reviewCounter;
    }

    public void setReviewCounter(int reviewCounter) {
        this.reviewCounter = reviewCounter;
    }


}
