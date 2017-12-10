package com.ogulcan.newsniffermvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ogulcan.newsniffermvp.R;
import com.ogulcan.newsniffermvp.utils.WebViewClient;

import butterknife.BindView;

public class WebViewActivity extends AppCompatActivity {

   @BindView(R.id.web_view)
   WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);



    }

    @Override
    protected void onStart() {
        super.onStart();
        WebSettings webSettings;
        Intent intent = getIntent();
        if(intent.hasExtra("url")){
            webSettings=webView.getSettings();
            webView.setWebChromeClient(new WebChromeClient());
            webView.setWebViewClient(new WebViewClient());
            webSettings.setSupportZoom(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
            webView.loadUrl(intent.getStringExtra("url"));
        }
    }
}
