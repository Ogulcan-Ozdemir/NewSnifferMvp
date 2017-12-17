package com.ogulcan.newsniffermvp.ui.NewsRecyclerList;


import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

public class ScrollListener extends RecyclerView.OnScrollListener{

    private OnBottomOfListListener onBottomOfListListener;
    private boolean flag = false;
    private EditText editText;

    public interface OnBottomOfListListener {
        void onReachedEnd();
    }

    public ScrollListener(OnBottomOfListListener onBottomOfListListener,EditText editText)   {
        this.onBottomOfListListener = onBottomOfListListener;
        this.editText = editText;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if ( !recyclerView.canScrollVertically(1) && dy != 0) {
            if(flag){
                onBottomOfListListener.onReachedEnd();
                flag = false;
            }else {
                flag = true;
            }

        }





    }
}
