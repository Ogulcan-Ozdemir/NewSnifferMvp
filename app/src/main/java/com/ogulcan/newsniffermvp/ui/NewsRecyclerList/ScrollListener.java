package com.ogulcan.newsniffermvp.ui.NewsRecyclerList;


import android.support.v7.widget.RecyclerView;

public class ScrollListener extends RecyclerView.OnScrollListener{

    private OnBottomOfListListener onBottomOfListListener;


    public interface OnBottomOfListListener {
        void onReachedEnd();
    }

    public ScrollListener(OnBottomOfListListener onBottomOfListListener)   {
        this.onBottomOfListListener = onBottomOfListListener;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if ( !recyclerView.canScrollVertically(1) && dy != 0) {
            onBottomOfListListener.onReachedEnd();
        }

    }
}
