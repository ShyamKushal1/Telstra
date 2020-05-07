package com.e.telstra.interfaces;

import com.e.telstra.repository.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsInterface {
    @GET("facts.json")
    Call<Result> getNewsResult();
}
