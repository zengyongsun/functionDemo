package com.example.administrator.functiondemo.webview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.webkit.WebView;

import com.example.administrator.functiondemo.R;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : Zeyo
 * e-mail : zengyongsun@163.com
 * date   : 2018/7/1614:26
 * desc   :
 * version: 1.0
 */
public class WebViewActivity extends MvpActivity<WebViewView, WebViewPresenter> implements WebViewView {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        loadUrl();
    }

    @NonNull
    @Override
    public WebViewPresenter createPresenter() {
        return new WebViewPresenter();
    }


    public void setWebView() {


    }

    public void loadUrl() {
        webView.loadUrl("https://baidu.com");
    }
}