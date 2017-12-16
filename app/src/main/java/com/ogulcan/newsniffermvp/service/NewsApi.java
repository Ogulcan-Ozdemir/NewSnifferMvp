package com.ogulcan.newsniffermvp.service;


import com.ogulcan.newsniffermvp.model.NewsReponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("everything")
    Call<NewsReponseModel> sniffNews(@Query("q") String keywords, @Query("page") int pageNum, @Query("language") String lang, @Query("apiKey") String apiKey);

}
