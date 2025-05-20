package com.example.notedapp;

public class Review {
    private String username;
    private String comment;
    private float rating;
    private String date;

    //constructor
    public Review(String username, String comment, float rating, String date) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }

    //getters
    public String getUsername() { return username; }
    public String getComment() { return comment; }
    public float getRating() { return rating; }
}
