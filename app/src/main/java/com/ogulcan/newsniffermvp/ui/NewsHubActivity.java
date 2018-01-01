package com.ogulcan.newsniffermvp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ogulcan.newsniffermvp.R;
import com.ogulcan.newsniffermvp.model.ArticleModel;
import com.ogulcan.newsniffermvp.sniffNews.ISniffNewsView;
import com.ogulcan.newsniffermvp.sniffNews.SniffNewsPresenterImpl;
import com.ogulcan.newsniffermvp.ui.NewsRecyclerList.NewsListAdapter;
import com.ogulcan.newsniffermvp.ui.NewsRecyclerList.NewsRecyclerListClickListener;
import com.ogulcan.newsniffermvp.ui.NewsRecyclerList.OnItemClickListener;
import com.ogulcan.newsniffermvp.ui.NewsRecyclerList.ScrollListener;
import com.ogulcan.newsniffermvp.utils.FragmentCaller;
import com.ogulcan.newsniffermvp.utils.NetworkStatusReceiver;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

public class NewsHubActivity extends AppCompatActivity implements ISniffNewsView,OnItemClickListener,ScrollListener.OnBottomOfListListener,TextView.OnEditorActionListener{

    @BindView(R.id.newsList)
    RecyclerView recyclerView;

    @BindView(R.id.search_box)
    EditText editText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private static String TAG;
    private ProgressDialog dialog;
    private AlertDialog alertDialog;
    private SniffNewsPresenterImpl sniffNewsPresenter;
    private NewsListAdapter adapter;
    private NetworkStatusReceiver receiver;
    private ScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_hub);

        ButterKnife.bind(this);

        TAG=getLocalClassName();

        dialog= ProgressDialog.show(this,"Sniffing news...",null);
        receiver = new NetworkStatusReceiver();
        sniffNewsPresenter = new SniffNewsPresenterImpl(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        adapter=new NewsListAdapter(new ArrayList<ArticleModel>(),this);
        recyclerView.setAdapter(adapter);

        scrollListener =  new ScrollListener(this,llm);
        recyclerView.addOnItemTouchListener(new NewsRecyclerListClickListener(this));
        recyclerView.addOnScrollListener(scrollListener);


        editText.setImeActionLabel("Search",IME_ACTION_DONE);
        editText.setOnEditorActionListener(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        alertDialog = makeAlertDialog();

        if(receiver.isConnected(this)){
            sniffNewsPresenter.startSearchNews();
        }else {
            alertDialog.show();
        }


    }


    @Override
    public void onIncomingNews(ArrayList<ArticleModel> articles,boolean isFeedChange) {

        if(isFeedChange){
            adapter.notifyItemRangeRemoved(0,adapter.getItemCount());
        }

        adapter.addMoreNews(articles);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
        setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void onError(String error){
        dialog.dismiss();
        Toast.makeText(this,"Error accured while fetching news ",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onError: "+error);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnScrollListener(scrollListener);
        dialog.dismiss();
        if(receiver.isConnected(this)){
//           sniffNewsPresenter.startSearchNews();
        }else {
            alertDialog.show();
        }

    }



    @Override
    protected void onPause() {
        super.onPause();
        recyclerView.removeOnScrollListener(scrollListener);
    }





    @Override
    public void onBackPressed() {
//        getSupportFragmentManager().beginTransaction().remove(detailsFragment).commit();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }



    }

    @Override
    public void itemClicked(View view, int position) {

        ShowDetailsFragment detailsFragment = ShowDetailsFragment.newInstance(sniffNewsPresenter.selectedArticle(position).getUrl());
        FragmentCaller.showFragment(getSupportFragmentManager(), detailsFragment,R.id.fragment_frame);

    }

    @Override
    public void onReachedEnd() {
        sniffNewsPresenter.getMoreNews();
    }

    @Override
    public boolean onEditorAction(TextView view, int actionId, KeyEvent keyEvent) {

        if (actionId == IME_ACTION_DONE) {

            if(!view.getText().toString().equals("")){
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                sniffNewsPresenter.searchForThis(view.getText().toString());
                dialog.show();
            }else {
                Toast.makeText(this,"You didn't enter anything for search",Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        return false;
    }

    public AlertDialog makeAlertDialog(){

           return new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setTitle("Network connection lost")
                    .setPositiveButton("Open Network connection",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            }
                    )
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            }
                    )
                    .create();

    }

}
