package com.ogulcan.newsniffermvp.sniffNews.NewsInteractor;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.ogulcan.newsniffermvp.model.NewsReponseModel;
import com.ogulcan.newsniffermvp.service.NewsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SniffNewsInteractorImpl implements Callback<NewsReponseModel>{

    private ResponseListener listener;
    // TODO: 3.12.2017  url resource içinde olmalı 
    private static String url=" https://newsapi.org/v2/";
    private static String api_key=yourapikey;

    public SniffNewsInteractorImpl( final ResponseListener listener){
        this.listener=listener;

        Gson gson =new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //todo Repository layer eklencek
        NewsApi api= retrofit.create(NewsApi.class);

        Call<NewsReponseModel> request= api.sniffNews("Teb",api_key);
        request.enqueue(this);

    }


    @Override
    public void onResponse(Call<NewsReponseModel> call, Response<NewsReponseModel> response) {
        if(response.isSuccessful()){
            //todo delete this uncessary bussiness logic implemeted just for mvp structure
            ArrayList<ArticleModel> articles=new ArrayList<>();
            for(ArticleModel article: response.body().getArticles()){
                if(article.getAuthor()!=null){
                    articles.add(article);
                }
            }
            listener.onNewSniffed(articles.toArray(new ArticleModel[articles.size()]));
        }else {
            listener.onNewsResponseError(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<NewsReponseModel> call, Throwable t) {
        listener.onRequestFailure(t.getLocalizedMessage());
    }
}
