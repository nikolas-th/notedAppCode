package com.example.notedapp;

import java.util.List;

public class Release {
    public int id;
    public String title;
    public int imageId;
    public String rating;
    public int year;
    public String artist;
    public String releaseType;
    public String [] tracklist;
    public String description;
    public String genre;
    private List<Review> reviews;

    public Release(int id, String title, int imageId, String rating, int year, String artist, String releaseType, String[] tracklist, String description,
                   String genre) {
        this.id = id;
        this.title = title;
        this.imageId = imageId;
        this.rating = rating;
        this.year = year;
        this.artist = artist;
        this.releaseType = releaseType;
        this.tracklist = tracklist != null ? tracklist : new String[0]; //If tracklist isn't provided, create empty array.
        this.description = description;
        this.genre = genre;
    }

    public Release(String title, int imageId, String rating, String artist, int year, String releaseType) {
        this.title = title;
        this.imageId = imageId;
        this.rating = rating;
        this.artist = artist;
        this.year = year;
        this.releaseType = releaseType;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public int getReviewCount() {
        return reviews.size();
    }

    
}
 


