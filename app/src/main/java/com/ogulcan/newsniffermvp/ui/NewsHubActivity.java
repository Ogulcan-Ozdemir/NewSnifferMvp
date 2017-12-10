package com.ogulcan.newsniffermvp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ogulcan.newsniffermvp.R;
import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.ogulcan.newsniffermvp.sniffNews.ISniffNewsView;
import com.ogulcan.newsniffermvp.sniffNews.SniffNewsPresenterImpl;
import com.ogulcan.newsniffermvp.ui.NewsRecyclerList.NewsListAdapter;
import com.ogulcan.newsniffermvp.ui.NewsRecyclerList.NewsRecyclerListClickListener;
import com.ogulcan.newsniffermvp.ui.NewsRecyclerList.OnItemClickListener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsHubActivity extends AppCompatActivity implements ISniffNewsView,ShowDetailsFragment.OnFragmentInteractionListener,OnItemClickListener{

    @BindView(R.id.newsList)
    RecyclerView recyclerView;

    private static String TAG;

    private  ShowDetailsFragment detailsFragment;

    private ProgressDialog dialog;
    private SniffNewsPresenterImpl sniffNewsPresenter;
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_hub);
        ButterKnife.bind(this);

        TAG=getLocalClassName();

        dialog= ProgressDialog.show(this,"Sniffing news...",null);

        if(detailsFragment == null) {
            detailsFragment = (ShowDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
        }

        sniffNewsPresenter = new SniffNewsPresenterImpl(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


         recyclerView.addOnItemTouchListener(new NewsRecyclerListClickListener(this));
    }


    @Override
    public void onIncomingNews(ArticleModel[] articles) {
        if(adapter==null){
            adapter=new NewsListAdapter(Arrays.asList(articles),getBaseContext());
            recyclerView.setAdapter(adapter);
        }else {
            adapter.addMoreNews(Arrays.asList(articles));
        }
        dialog.dismiss();
    }

    @Override
    public void onError(String error){
        dialog.dismiss();
        Toast.makeText(this,"Error accured while fetching news ",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onError: "+error);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        dialog.dismiss();
    }

    @Override
    public void onFragmentInteraction() {
      Log.d(TAG,"fragment");
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        dialog=ProgressDialog.show(this,"Getting details ...",null);
//        detailsFragment = ShowDetailsFragment.newInstance(sniffNewsPresenter.selectedArticle(i).getUrl());
//        FragmentCaller.showFragment(getSupportFragmentManager(),detailsFragment,R.id.fragment_frame);
////        Intent intent= new Intent(this,WebViewActivity.class);
////        intent.putExtra("url",sniffNewsPresenter.selectedArticle(i).getUrl());
////        startActivity(intent);
//    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public void itemClicked(View view, int position) {
        dialog=ProgressDialog.show(this,"Getting details ...",null);
        Intent intent= new Intent(this,WebViewActivity.class);
        intent.putExtra("url",sniffNewsPresenter.selectedArticle(position).getUrl());
        startActivity(intent);
    }
}
