package com.ogulcan.newsniffermvp.model;



public class NewsReponseModel {
    private String status;
    private ArticleModel[] articles;

    public NewsReponseModel(){}

    public NewsReponseModel(String status, ArticleModel[] articles) {
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArticleModel[] getArticles() {
        return articles;
    }

    public void setArticles(ArticleModel[] articles) {
        this.articles = articles;
    }
}
