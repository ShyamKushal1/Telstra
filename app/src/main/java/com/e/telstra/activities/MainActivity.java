package com.e.telstra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.e.telstra.R;
import com.e.telstra.adapter.NewsListAdapter;
import com.e.telstra.model.NewsModel;
import com.e.telstra.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private NewsRepository newsRepository;
    private NewsListAdapter newsListAdapter;
    private List<NewsModel> newsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        newsRepository = new ViewModelProvider(this).get(NewsRepository.class);
        getData();
    }

    private void getData() {
        newsRepository.getAllData().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                Log.d("activity", String.valueOf(newsModels.size()));
                newsListAdapter = new NewsListAdapter(MainActivity.this, newsModels);
                listView.setAdapter(newsListAdapter);
                //newsListAdapter.setNewsList(newsModels);
            }
        });
    }
}
