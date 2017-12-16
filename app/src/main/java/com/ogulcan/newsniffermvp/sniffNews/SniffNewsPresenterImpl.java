package com.ogulcan.newsniffermvp.sniffNews;


import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.ogulcan.newsniffermvp.sniffNews.NewsInteractor.ResponseListener;
import com.ogulcan.newsniffermvp.sniffNews.NewsInteractor.SniffNewsInteractorImpl;

import java.util.ArrayList;

public class SniffNewsPresenterImpl implements ISniffNewsPresenter,ResponseListener{

    private ISniffNewsView view;
    private SniffNewsInteractorImpl newsInteractor;

    public SniffNewsPresenterImpl(ISniffNewsView view){
        this.view=view;
        newsInteractor = new SniffNewsInteractorImpl(this);
    }


    @Override
    public void onNewSniffed(ArrayList<ArticleModel> articles,boolean isFeedChange) {
        view.onIncomingNews(articles,isFeedChange);
    }

    @Override
    public void onNewsResponseError(String error) {
         view.onError(error);
    }

    @Override
    public void onRequestFailure(String error) {
         view.onError(error);
    }

    @Override
    public ArticleModel selectedArticle(Integer id){
        return newsInteractor.getArticles().get(id);
    }

    @Override
    public void getMoreNews() {
       newsInteractor.getMoreNews();
    }

    @Override
    public void searchForThis(String input) {
         newsInteractor.initNewSearch(input);
    }
}
