package com.example.notedapp;

public class Event {
    private String title;
    private String artist;
    private String genre;
    private String city;
    private String location;
    private String date;
    private String time;

    public Event(String title, String artist, String genre, String city, String location, String date, String time) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.city = city;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
