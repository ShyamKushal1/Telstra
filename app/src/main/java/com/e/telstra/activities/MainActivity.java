package com.e.telstra.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.e.telstra.R;
import com.e.telstra.adapter.NewsListAdapter;
import com.e.telstra.common.NetworkUtil;
import com.e.telstra.fragments.NewsDetailFragment;
import com.e.telstra.model.NewsModel;
import com.e.telstra.repository.NewsRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {


    public static final String NEWS_FRAGMENT = "NEWS_FRAGMENT";
    private NewsRepository newsRepository;
    private NewsListAdapter newsListAdapter;
    private ListView listView;
    private FrameLayout newsDetails;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<NewsModel> newsList;
    private NetworkUtil networkUtil = new NetworkUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        newsDetails = findViewById(R.id.fragmentContainer);
        swipeRefreshLayout = findViewById(R.id.pull_Refresh);
        newsRepository = new ViewModelProvider(this).get(NewsRepository.class);

        if (networkUtil.checkInternetConnection(this)) {
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
        } else
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show();

        listView.setOnItemClickListener(this);

    }

    private void getData() {
        newsRepository.getAllData().observe(this, new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                Log.d("activity", String.valueOf(newsModels.size()));
                newsList = newsModels;
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        newsDetails.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
        NewsModel newsModel = newsList.get(position);
        NewsDetailFragment newsDetailFragment = NewsDetailFragment.newInstance(newsModel);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, newsDetailFragment, NEWS_FRAGMENT);
        fragmentTransaction.addToBackStack(newsDetailFragment.getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            newsDetails.setVisibility(View.GONE);

        } else {
            super.onBackPressed();
        }
    }
}
