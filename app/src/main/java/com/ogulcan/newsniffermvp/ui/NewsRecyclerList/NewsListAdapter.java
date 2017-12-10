package com.ogulcan.newsniffermvp.ui.NewsRecyclerList;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogulcan.newsniffermvp.R;
import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<ArticleModel> articleModelList;
    private Context context;

    public NewsListAdapter( List<ArticleModel> articleModelList,Context context){
        this.articleModelList = articleModelList;
        this.context = context;
    }



    public void addMoreNews(List<ArticleModel> newListItems){
        articleModelList.addAll(newListItems);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row,parent, false);
        return new NewsViewHolder(v);

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        ArticleModel article = articleModelList.get(position);
        Picasso.with(context).load(article.getUrlToImage()).fit().into(holder.news_image);
        holder.news_text.setText(article.getTitle());
        //todo into the string resource
        holder.news_source.setText("Source:  "+article.getSource().getName());
    }

    @Override
    public int getItemCount() {
        return articleModelList.size();
    }
}
