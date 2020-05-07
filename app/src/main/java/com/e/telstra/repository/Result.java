package com.e.telstra.repository;

import com.e.telstra.model.NewsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("rows")
    @Expose
    private List<NewsModel> newsResult = null;

    public List<NewsModel> getNewsResult() {
        return newsResult;
    }

    public void setNewsResult(List<NewsModel> newsResult) {
        this.newsResult = newsResult;
    }
}
