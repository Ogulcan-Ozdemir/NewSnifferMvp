package com.ogulcan.newsniffermvp.sniffNews;


import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.ogulcan.newsniffermvp.sniffNews.NewsInteractor.ResponseListener;
import com.ogulcan.newsniffermvp.sniffNews.NewsInteractor.SniffNewsInteractorImpl;

public class SniffNewsPresenterImpl implements ISniffNewsPresenter,ResponseListener{

    private ISniffNewsView view;
    private SniffNewsInteractorImpl newsInteractor;

    public SniffNewsPresenterImpl(ISniffNewsView view){
        this.view=view;
        newsInteractor = new SniffNewsInteractorImpl(this);
    }


    @Override
    public void onNewSniffed(ArticleModel[] articles) {
        view.onIncomingNews(articles);
    }

    @Override
    public void onNewsResponseError(String error) {
         view.onError(error);
    }

    @Override
    public void onRequestFailure(String error) {
         view.onError(error);
    }
}
