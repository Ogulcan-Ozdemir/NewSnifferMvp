package com.ogulcan.newsniffermvp.ui.NewsRecyclerList;


import android.support.v7.widget.RecyclerView;

public class ScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager layoutManager;

    public ScrollListener(RecyclerView.LayoutManager layoutManager)   {
        this.layoutManager = layoutManager;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
//        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
//        if (pastVisibleItems + visibleItemCount >= totalItemCount) {
//                //End of list
//        }

    }
}
