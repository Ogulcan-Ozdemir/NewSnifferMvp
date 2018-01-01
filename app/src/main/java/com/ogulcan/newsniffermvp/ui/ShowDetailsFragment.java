package com.ogulcan.newsniffermvp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ogulcan.newsniffermvp.R;
import com.ogulcan.newsniffermvp.utils.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowDetailsFragment extends Fragment {

    @BindView(R.id.webView)
    WebView webView;

    private WebSettings webSettings;
    private static final String news_url_param = "url";
    private String url;
    private ProgressDialog progressDialog;


    public ShowDetailsFragment() {
        // Required empty public constructor
    }


    public static ShowDetailsFragment newInstance(String url) {
        ShowDetailsFragment fragment = new ShowDetailsFragment();
        Bundle args = new Bundle();
        args.putString(news_url_param, url);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(news_url_param);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
//        setRetainInstance(true);
        View fragmentView=inflater.inflate(R.layout.fragment_show_details, container, false);
        progressDialog = ProgressDialog.show(getActivity(),"Getting details...",null);
        ButterKnife.bind(this,fragmentView);

        webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(progressDialog));
        webView.loadUrl(url);

        webView.getSettings().setDomStorageEnabled(true);

        return fragmentView;
    }



    @Override
    public void onStart() {
        super.onStart();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



}
