package com.example.notedapp;

public class Release {
    public String title;
    public int imageId;
    public String rating;
    public int year;
    public String artist;
    public String releaseType;
    public String [] tracklist;
    public String description;
    public String genre;

    public Release(String title, int imageId, String rating, int year, String artist, String releaseType, String[] tracklist, String description, String genre) {
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

    public Release(String title, int imageId, String rating) {
        this.title = title;
        this.imageId = imageId;
        this.rating = rating;
    }
    
}
 


