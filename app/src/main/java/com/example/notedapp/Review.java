package com.example.notedapp;


public class Review {
    private String username;
    private String comment;
    private String rating;
    private String date;

    private int releaseId;

    //constructor
    public Review(String username, String comment, String rating, String date, int releaseId) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.releaseId = releaseId;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public String getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public int getReleaseId() {
        return releaseId;
    }

}
