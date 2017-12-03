package com.ogulcan.newsniffermvp.sniffNews.NewsInteractor;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ogulcan.newsniffermvp.model.NewsReponseModel;
import com.ogulcan.newsniffermvp.service.NewsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SniffNewsInteractorImpl implements Callback<NewsReponseModel>{

    private ResponseListener listener;
    // TODO: 3.12.2017  url resource içinde olmalı 
    private static String url=" https://newsapi.org/v2/";
    private static String api_key="fa2ff70b3650472e96f7c8e16e0f6226";

    public SniffNewsInteractorImpl( final ResponseListener listener){
        this.listener=listener;

        Gson gson =new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NewsApi api= retrofit.create(NewsApi.class);

        Call<NewsReponseModel> request= api.sniffNews("Teb",api_key);
        request.enqueue(this);

    }


    @Override
    public void onResponse(Call<NewsReponseModel> call, Response<NewsReponseModel> response) {
        if(response.isSuccessful()){
            listener.onNewSniffed(response.body());
        }else {
            listener.onNewsResponseError(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<NewsReponseModel> call, Throwable t) {
        listener.onRequestFailure(t.getLocalizedMessage());
    }
}
