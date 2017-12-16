package com.ogulcan.newsniffermvp.sniffNews;


import com.ogulcan.newsniffermvp.model.ArticleModel;

public interface ISniffNewsPresenter {
       ArticleModel selectedArticle(Integer id);
       void getMoreNews();
       void searchForThis(String input);
}
