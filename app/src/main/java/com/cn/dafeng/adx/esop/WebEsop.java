package com.cn.dafeng.adx.esop;

import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.cn.dafeng.adx.esop.utils.InitUtil.initWebview;

public class WebEsop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        initWebview(webView);
        setContentView(webView);
        webView.loadUrl(getIntent().getStringExtra("url") );
    }


}