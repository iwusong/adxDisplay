package com.cn.dafeng.adx.esop;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import static com.cn.dafeng.adx.esop.utils.InitUtil.initWebview;

public class WebEsop extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        initWebview(webView);
        setContentView(webView);
        webView.clearCache(true);
        webView.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU | keyCode == KeyEvent.KEYCODE_CTRL_LEFT) {

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        System.out.println("onDestroy");
        if (webView != null) {
            //加载null内容
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            //清除历史记录
            webView.clearHistory();
            //移除WebView
            ((ViewGroup) webView.getParent()).removeView(webView);
            //销毁VebView
            webView.destroy();
            //WebView置为null
            webView = null;
        }
        super.onDestroy();
    }
}
