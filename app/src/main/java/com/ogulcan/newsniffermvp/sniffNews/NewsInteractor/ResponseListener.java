package com.ogulcan.newsniffermvp.sniffNews.NewsInteractor;

import com.ogulcan.newsniffermvp.model.ArticleModel;


public interface ResponseListener {
    void onNewSniffed(ArticleModel[] articles);
    void onNewsResponseError(String error);
    void onRequestFailure(String error);
}
