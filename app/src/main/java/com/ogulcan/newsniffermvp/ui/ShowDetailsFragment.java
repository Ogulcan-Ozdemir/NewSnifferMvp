package com.ogulcan.newsniffermvp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ogulcan.newsniffermvp.R;


public class ShowDetailsFragment extends Fragment implements View.OnKeyListener{


    WebView webView;

    private WebSettings webSettings;

    private static final String news_url_param = "url";
    private String url;


    private OnFragmentInteractionListener mListener;

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

        View fragmentView=inflater.inflate(R.layout.fragment_show_details, container, false);
        webView = fragmentView.findViewById(R.id.webView);
        fragmentView.setOnKeyListener(this);
            webSettings=webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new WebViewClient());
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if( i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            return true;
        }
        return false;

    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction();
    }
}
