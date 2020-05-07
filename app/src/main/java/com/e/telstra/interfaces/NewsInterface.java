package com.e.telstra.interfaces;

import com.e.telstra.model.NewsModel;
import com.e.telstra.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {
    @GET()
    Call<Result> getNewsResult();
}
