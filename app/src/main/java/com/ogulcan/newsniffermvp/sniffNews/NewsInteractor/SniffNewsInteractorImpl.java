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

public class SniffNewsInteractorImpl implements Callback<NewsReponseModel>,SniffNewsInteractor{

    private ResponseListener listener;
    // TODO: 3.12.2017  url resource içinde olmalı 
    private static String url=" https://newsapi.org/v2/";
    private static String api_key="fa2ff70b3650472e96f7c8e16e0f6226";


    //TODO this should come from local repo not  from intreactor
    private ArrayList<ArticleModel> articles;
    private static  NewsApi api;

    //it starts 1
    private int currentPage=1;
    private String searchText;

    public SniffNewsInteractorImpl( final ResponseListener listener){
        this.listener=listener;

        articles = new ArrayList<>();

        Gson gson =new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //todo Repository layer eklencek
        api= retrofit.create(NewsApi.class);

        //todo keep search text in preferences
        searchText = "Teb";

    }

    @Override
    public void startSearchingNews() {
        makeNewResponse(searchText);
    }

    private void makeNewResponse(String searchText){
        Call<NewsReponseModel> request= api.sniffNews(searchText,currentPage,"en",api_key);
        request.enqueue(this);
    }


    @Override
    public void onResponse(Call<NewsReponseModel> call, Response<NewsReponseModel> response) {
        if(response.isSuccessful()){
            //todo delete this uncessary bussiness logic implemeted just for mvp structure

            for(ArticleModel article: response.body().getArticles()){
                if(article.getAuthor()!=null &&  article.getUrlToImage() != null && article.getUrlToImage().contains("http") ){
                    articles.add(article);
                }
            }

            if(articles.size()<20){
                listener.onNewSniffed(articles,true);
            }else {
                listener.onNewSniffed(articles,false);
            }

        }else {
            listener.onNewsResponseError(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<NewsReponseModel> call, Throwable t) {
        listener.onRequestFailure(t.getLocalizedMessage());
    }

    public ArrayList<ArticleModel> getArticles() {
        return articles;
    }

    @Override
    public void getMoreNews() {
        currentPage+=1;
        makeNewResponse(searchText);
    }

    @Override
    public void initNewSearch(String input) {
        articles.clear();
        currentPage=1;
        searchText=input;
        makeNewResponse(searchText);
    }




}
