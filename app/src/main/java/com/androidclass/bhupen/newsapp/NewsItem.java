package com.androidclass.bhupen.newsapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private String url;
    private String date;

    public NewsItem(int id, String title, String description, String url, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.date = date;
    }

    @Ignore
    public NewsItem(String title, String description, String url, String date) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.date = date;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
