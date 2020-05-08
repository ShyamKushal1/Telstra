package com.e.telstra.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.e.telstra.R;
import com.e.telstra.databinding.FragmentLayoutBinding;
import com.e.telstra.model.NewsDetailModel;
import com.e.telstra.model.NewsModel;

import java.io.Serializable;

public class NewsDetailFragment extends Fragment {

    private static final String NEWS_ITEM = "item_key";
    private NewsModel newsModel;
    private NewsDetailModel detailModel;

    public static NewsDetailFragment newInstance(NewsModel newsModel) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NEWS_ITEM, (Serializable) newsModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FragmentLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_layout, container, false);
        newsModel= (NewsModel) getArguments().get(NEWS_ITEM);
        detailModel=new NewsDetailModel(newsModel.getTitle(),newsModel.getDescription(),newsModel.getImage());
        binding.setDetail(detailModel);
        return binding.getRoot();
    }

}
