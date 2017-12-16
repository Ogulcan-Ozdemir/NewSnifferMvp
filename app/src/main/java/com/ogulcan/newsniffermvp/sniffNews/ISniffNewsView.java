package com.ogulcan.newsniffermvp.sniffNews;


import com.ogulcan.newsniffermvp.model.ArticleModel;

import java.util.ArrayList;

public interface ISniffNewsView {
    void onIncomingNews(ArrayList<ArticleModel> model,boolean isFeedChange);
    void onError(String error);

}
