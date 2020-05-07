package com.e.telstra.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.e.telstra.R;
import com.e.telstra.databinding.ListItemsBinding;
import com.e.telstra.model.NewsModel;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    ConstraintLayout constraintLayout;
    TextView tvTitle, tvDisc;
    ImageView ivImage;
    Context context;
    private List<NewsModel> newsModels;
    ListItemsBinding listItemsBinding;

    public NewsListAdapter(Context context, List<NewsModel> newsModels) {
        this.context = context;
        this.newsModels = newsModels;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        NewsModel newsModel = newsModels.get(position);
        if (inflater == null) {
            inflater = (LayoutInflater) viewGroup.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        listItemsBinding = DataBindingUtil.inflate(inflater, R.layout.list_items, viewGroup, false);

        Log.d("adapter", newsModel.getDescription() + " " + newsModel.getTitle());
        if (newsModel.getTitle() != null)
            listItemsBinding.setNews(newsModel);

        return listItemsBinding.getRoot();
    }

    @Override
    public int getCount() {
        return newsModels == null ? 0 : newsModels.size();
    }

    @Override
    public NewsModel getItem(int i) {
        return newsModels == null ? null : i < 0 ? null : i < newsModels.size() ? newsModels.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void setNewsList(List<NewsModel> newsModels) {
        this.newsModels = newsModels;
        Log.d("adapter", String.valueOf(newsModels.size()));
    }
}
