package com.ogulcan.newsniffermvp.sniffNews.NewsInteractor;

import com.ogulcan.newsniffermvp.model.ArticleModel;

import java.util.ArrayList;


public interface ResponseListener {
    void onNewSniffed(ArrayList<ArticleModel> articles,boolean isFeedChange);
    void onNewsResponseError(String error);
    void onRequestFailure(String error);
}
