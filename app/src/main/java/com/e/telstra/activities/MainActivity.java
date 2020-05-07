package com.e.telstra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.e.telstra.R;
import com.e.telstra.adapter.NewsListAdapter;
import com.e.telstra.model.NewsModel;
import com.e.telstra.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private NewsRepository newsRepository;
    private NewsListAdapter newsListAdapter;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        swipeRefreshLayout=findViewById(R.id.pull_Refresh);
        newsRepository = new ViewModelProvider(this).get(NewsRepository.class);
        getData();
        swipeRefreshLayout.setOnRefreshListener(this);
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        getData();
                                    }
                                }
        );
    }

    private void getData() {
        newsRepository.getAllData().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                Log.d("activity", String.valueOf(newsModels.size()));
                newsListAdapter = new NewsListAdapter(MainActivity.this, newsModels);
                listView.setAdapter(newsListAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
