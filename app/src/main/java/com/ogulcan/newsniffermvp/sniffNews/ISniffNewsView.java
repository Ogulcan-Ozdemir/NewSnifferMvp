package com.ogulcan.newsniffermvp.sniffNews;


import com.ogulcan.newsniffermvp.model.ArticleModel;

public interface ISniffNewsView {
    void onIncomingNews(ArticleModel[] model);
    void onError(String error);

}
