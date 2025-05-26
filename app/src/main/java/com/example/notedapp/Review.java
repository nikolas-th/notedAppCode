package com.example.notedapp;


public class Review {
    private int user_id;
    private String username;
    private String comment;
    private String rating;
    private String date;
    private int releaseId;

    //constructor
    public Review(int user_id, String comment, String rating, String date, int releaseId) {
        this.user_id = user_id;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.releaseId = releaseId;
        this.username = DBmanager.getUserById(user_id).getUsername();
    }

    //getters
    public int getUserId() {
        return user_id;
    }

    public String getUsername(){return username;}

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
