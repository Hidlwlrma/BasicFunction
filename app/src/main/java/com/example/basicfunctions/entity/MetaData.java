package com.example.basicfunctions.entity;

public class MetaData {
    String title;
    String author;
    String publishTime;
    int type;
    String cover;
    String[] covers;

    public MetaData(String title, String author, String publishTime, int type, String cover) {
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.type = type;
        this.cover = cover;
    }

    public MetaData(String title, String author, String publishTime, int type, String[] covers) {
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.type = type;
        this.covers = covers;
    }

    public MetaData(String title, String author, String publishTime, int type) {
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public int getType() {
        return type;
    }

    public String getCover() {
        return cover;
    }

    public String[] getCovers() {
        return covers;
    }
}
