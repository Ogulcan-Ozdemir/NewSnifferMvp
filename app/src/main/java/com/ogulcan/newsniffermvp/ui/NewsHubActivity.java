package com.ogulcan.newsniffermvp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ogulcan.newsniffermvp.R;
import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.ogulcan.newsniffermvp.sniffNews.ISniffNewsView;
import com.ogulcan.newsniffermvp.sniffNews.SniffNewsPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsHubActivity extends AppCompatActivity implements ISniffNewsView{

    @BindView(R.id.newsList)
    ListView newsList;

    private static String TAG;

    private ProgressDialog dialog;
    private SniffNewsPresenterImpl sniffNewsPresenter;
    private  ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_hub);
        ButterKnife.bind(this);

        TAG=getLocalClassName();

        dialog= ProgressDialog.show(this,"Sniffing news...",null);

        sniffNewsPresenter = new SniffNewsPresenterImpl(this);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        newsList.setAdapter(adapter);
    }


    @Override
    public void onIncomingNews(ArticleModel[] articles) {
        if(articles.length>0){
            for (ArticleModel article : articles) {
                adapter.add(article.getDescription());
            }
            adapter.notifyDataSetChanged();
        }
        dialog.dismiss();
    }

    @Override
    public void onError(String error){
        dialog.dismiss();
        Toast.makeText(this,"Error accured while fetching news ",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onError: "+error);
    }
}
