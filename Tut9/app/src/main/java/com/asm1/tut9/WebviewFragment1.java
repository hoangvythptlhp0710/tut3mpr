package com.asm1.tut9;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebviewFragment#} factory method to
 * create an instance of this fragment.
 */
public class WebviewFragment extends Fragment {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        Bundle args = getArguments();
        String url = args.getString("url");

        WebView webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        // enable js

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);

        webView.loadUrl("url");

        return view;
    }


}