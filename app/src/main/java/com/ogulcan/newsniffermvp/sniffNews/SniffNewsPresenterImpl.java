package com.ogulcan.newsniffermvp.sniffNews;


import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.ogulcan.newsniffermvp.model.NewsReponseModel;
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
    public void onNewSniffed(NewsReponseModel model) {
        ArrayList<ArticleModel> articles=new ArrayList<>();
        //todo delete this uncessary bussiness logic implemeted just for mvp structure
        for(ArticleModel article: model.getArticles()){
            if(article.getAuthor()!=null){
                articles.add(article);
            }
        }
        view.onIncomingNews(articles.toArray(new ArticleModel[articles.size()]));
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
