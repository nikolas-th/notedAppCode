package com.example.notedapp;


import java.util.ArrayList;
import java.util.List;

public class ReleaseList{

    private String author;
    private String title;
    private String description;
    private List<Release> releasesList;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ReleaseList(String author, String title, String description, List<Release> releasesList, String date) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.releasesList = releasesList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Release> getReleasesList() {
        return releasesList;
    }

    // Add this to your ReleaseList class
    public ReleaseList copy() {
        return new ReleaseList(this.author, this.title, this.description, this.releasesList, this.date);
    }
    public void setReleasesList(List<Release> releasesList) {
        this.releasesList = releasesList;
    }
}
