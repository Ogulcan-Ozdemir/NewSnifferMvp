package com.ogulcan.newsniffermvp.sniffNews.NewsInteractor;

import com.ogulcan.newsniffermvp.model.NewsReponseModel;


public interface ResponseListener {
    void onNewSniffed(NewsReponseModel articles);
    void onNewsResponseError(String error);
    void onRequestFailure(String error);
}
