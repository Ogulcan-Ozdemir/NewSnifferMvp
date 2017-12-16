package com.ogulcan.newsniffermvp.utils;


import android.app.ProgressDialog;
import android.webkit.WebView;

public class WebViewClient extends android.webkit.WebViewClient{

    private ProgressDialog dialog;

    public WebViewClient(ProgressDialog dialog){
        this.dialog = dialog;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        dialog.dismiss();
    }
}
