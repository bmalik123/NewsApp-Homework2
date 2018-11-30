package com.androidclass.bhupen.newsapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androidclass.bhupen.newsapp.NewsItem;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Query("SELECT * FROM news_item ORDER BY title ASC")
    LiveData<List<NewsItem>> loadAllNewsItems();

    @Insert
    void insert(List<NewsItem> newsItem);


    @Query("DELETE FROM news_item")
    void clearAll();


}
