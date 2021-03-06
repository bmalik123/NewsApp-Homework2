package com.androidclass.bhupen.newsapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidclass.bhupen.newsapp.NewsItem;

@Database(entities = {NewsItem.class}, version = 1)
public abstract class NewsItemRoomDatabase extends RoomDatabase {

    public abstract NewsItemDao newsItemDao();

    private static volatile NewsItemRoomDatabase INSTANCE;

    static NewsItemRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsItemRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsItemRoomDatabase.class, "news_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}