package com.e.telstra.manager;

import com.e.telstra.interfaces.NewsInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceManager {
    private static Retrofit retrofit;
    private static final String BASE_URL="https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    public static NewsInterface getService(){

        if (retrofit==null){
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(NewsInterface.class);
    }
}
