package com.e.telstra.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.telstra.interfaces.NewsInterface;
import com.e.telstra.manager.ServiceManager;
import com.e.telstra.model.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository extends AndroidViewModel {

    public NewsRepository(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<NewsModel>> mutableLiveData = new MutableLiveData<>();


    public MutableLiveData<List<NewsModel>> getMutableLiveData(){

        final NewsInterface newsInterface= ServiceManager.getService();

        Call<Result> call=newsInterface.getNewsResult();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result=response.body();
                if (result!=null && result.getNewsResult()!=null){
                    mutableLiveData.setValue(result.getNewsResult());
                    List<NewsModel> list=result.getNewsResult();
                    for (NewsModel a : list) {
                        Log.i("News List",a.getDescription()+" "+a.getTitle()+" "+a.getImage());
                    }

                }
                Log.i("url ", String.valueOf(call.request().url()));
                Log.i("Success","successfully downloaded");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("Error","Failed To Download");

            }
        });
        return mutableLiveData;
    }

    public LiveData<List<NewsModel>> getAllData() {
        return getMutableLiveData();
    }
}
