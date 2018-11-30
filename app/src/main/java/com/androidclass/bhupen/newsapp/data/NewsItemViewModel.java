package com.androidclass.bhupen.newsapp.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.androidclass.bhupen.newsapp.NewsItem;

import java.net.URL;
import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private NewsItemRepository mRepository;

    private LiveData<List<NewsItem>> mAllNewsItem;

    public NewsItemViewModel(Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mAllNewsItem = mRepository.getmAllNewsItem();
    }

    public LiveData<List<NewsItem>> getmAllNewsItem() {
        mAllNewsItem = mRepository.getmAllNewsItem();
        return mAllNewsItem;
    }

    /* public void insert(Word word) {
         mRepository.insert(word);
     }

     public void delete(Word word){
         mRepository.delete(word);
     }*/
    public void newsItemSync(URL url) {
        mRepository.newsItemSync(url);
    }

}

