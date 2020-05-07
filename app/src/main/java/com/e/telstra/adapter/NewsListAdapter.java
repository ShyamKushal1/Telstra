package com.e.telstra.adapter;

import android.content.Context;
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
        NewsModel newsModel=newsModels.get(position);
        listItemsBinding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.activity_main, viewGroup,false);
        //view = inflater.inflate(R.layout.list_items, viewGroup, false);
        constraintLayout = view.findViewById(R.id.cl_Layout);
        hookViews(constraintLayout);
        listItemsBinding.setNews(newsModel);
        /*tvDisc.setText(newsModel.getDescription());
        ivImage.setImage(newsModel.getImage());*/
        return view;
    }

    private void hookViews(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View view = vg.getChildAt(i);
            if (view instanceof TextView) {
                switch (view.getId()) {
                    case R.id.tv_Title:
                        tvTitle = (TextView) view;
                        break;
                    case R.id.tv_Disc:
                        tvDisc = (TextView) view;
                        break;
                }
            }
            if (view instanceof ImageView) {
                switch (view.getId()) {
                    case R.id.iv_Image:
                        ivImage = (ImageView) view;
                        break;
                }
            }
        }

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
