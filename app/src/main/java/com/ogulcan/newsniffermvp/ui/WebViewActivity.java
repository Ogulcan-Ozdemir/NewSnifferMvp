package com.ogulcan.newsniffermvp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ogulcan.newsniffermvp.R;
import com.ogulcan.newsniffermvp.utils.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
     WebView webView;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        dialog= ProgressDialog.show(this,"Processing ...",null);

        WebSettings webSettings;
        Intent intent = getIntent();
        webSettings=webView.getSettings();
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(dialog));
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webView.loadUrl(intent.getStringExtra("url"));
    }

}
