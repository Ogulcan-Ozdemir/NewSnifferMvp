package com.ogulcan.newsniffermvp.ui.NewsRecyclerList;


import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

public class NewsRecyclerListClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener onItemClickListener;

    public NewsRecyclerListClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        if(e.getAction() == MotionEvent.ACTION_UP && rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE){
            View v=rv.findChildViewUnder(e.getX(), e.getY());
            onItemClickListener.itemClicked(v,rv.getChildAdapterPosition(v));
            return true;
        }

        return false;
    }



    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
       rv.computeHorizontalScrollExtent();
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


}
