package com.ogulcan.newsniffermvp.ui.NewsRecyclerList;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ScrollListener extends RecyclerView.OnScrollListener{

    private OnBottomOfListListener onBottomOfListListener;
    private LinearLayoutManager layoutManager;
    private int visibleThreshold = 5;

    private int previousTotalItemCount = 0;

    private boolean loading = false;


    public interface OnBottomOfListListener {
        void onReachedEnd();
    }

    public ScrollListener(OnBottomOfListListener onBottomOfListListener,LinearLayoutManager layoutManager)   {
        this.onBottomOfListListener = onBottomOfListListener;
        this.layoutManager = layoutManager;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition =  layoutManager.findLastVisibleItemPosition();

        if (totalItemCount < previousTotalItemCount) {

            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
               this.loading = true;
            }

        }

        if (loading && (totalItemCount > previousTotalItemCount)) {

            loading = false;
            previousTotalItemCount = totalItemCount;
            visibleThreshold = lastVisibleItemPosition - layoutManager.findFirstVisibleItemPosition();
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {

            onBottomOfListListener.onReachedEnd();
            loading = true;

        }


//        if ( !recyclerView.canScrollVertically(1) && dy != 0) {
//            if(flag){
//                onBottomOfListListener.onReachedEnd();
//                flag = false;
//            }else {
//                flag = true;
//            }
//
//        }





    }
}
