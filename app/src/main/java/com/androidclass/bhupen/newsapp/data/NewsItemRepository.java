package com.androidclass.bhupen.newsapp.data;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.androidclass.bhupen.newsapp.NewsItem;
import com.androidclass.bhupen.newsapp.utils.JsonUtils;
import com.androidclass.bhupen.newsapp.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {
    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItem;

    NewsItemRepository(Application application) {
        NewsItemRoomDatabase db = NewsItemRoomDatabase.getDatabase(application);
        mNewsItemDao = db.newsItemDao();
        mAllNewsItem = mNewsItemDao.loadAllNewsItems();
    }

    /***    Created a method that geta all the news items from the database (using NewsItemDao). It should do this in an AsyncTask.*/

    public LiveData<List<NewsItem>> getmAllNewsItem() {
        new newsItemsAsyncTask(mNewsItemDao).execute();
        return mAllNewsItem;
    }

    /***Created a method in that syncs the database with the news api*/

    public void newsItemSync(URL url) {
        new newsItemSyncAsyncTask(mNewsItemDao).execute(url);

    }

   /* public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void delete(Word word){
        new deleteAsyncTask(mWordDao).execute(word);
    }*/

    /*private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }*/

    /*private static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        deleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            Log.d("mycode", "deleteding word: " + params[0].getWord());
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }*/

    private class newsItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsItemDao mAsyncTaskDao;

        newsItemsAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAllNewsItem = mAsyncTaskDao.loadAllNewsItems();
            return null;
        }
    }

    private static class newsItemSyncAsyncTask extends AsyncTask<URL, Void, Void> {
        private NewsItemDao mAsyncTaskDao;

        newsItemSyncAsyncTask(NewsItemDao mNewsItemDao) {
            mAsyncTaskDao = mNewsItemDao;
        }

        @Override
        protected Void doInBackground(URL... urls) {
            String newsitemSearchResults = "";
            ArrayList<NewsItem> news = new ArrayList<>();


            try {
                newsitemSearchResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            mAsyncTaskDao.clearAll();
            news = JsonUtils.parseNews(newsitemSearchResults);
            mAsyncTaskDao.insert(news);
            return null;
        }
    }
}
