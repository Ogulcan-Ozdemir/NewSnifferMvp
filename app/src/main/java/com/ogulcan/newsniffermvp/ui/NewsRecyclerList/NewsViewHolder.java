package com.ogulcan.newsniffermvp.ui.NewsRecyclerList;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogulcan.newsniffermvp.R;

public class NewsViewHolder extends RecyclerView.ViewHolder{
    //todo try with butter knife
    public ImageView news_image;
    public TextView news_text;
    public TextView news_source;

    public NewsViewHolder(View itemView) {
        super(itemView);
        news_image = itemView.findViewById(R.id.newsImage);
        news_text = itemView.findViewById(R.id.newsTitleText);
        news_source = itemView.findViewById(R.id.newsSource);
    }
}
