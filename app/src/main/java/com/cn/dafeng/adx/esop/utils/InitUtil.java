package com.cn.dafeng.adx.esop.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.webkit.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.cn.dafeng.adx.esop.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class InitUtil {

    public static AlertDialog initAlertDialog(MainActivity context) {

        final EditText inputServer = new EditText(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("输入设备编码").setView(inputServer);
        builder.setPositiveButton("确定", (dialog, which) -> {
            SharedPreferences adx = context.getSharedPreferences("adx", MODE_PRIVATE);
            adx.edit().putString("id", inputServer.getText().toString()).apply();
            context.goEsop();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnKeyListener(
                (dialogInterface, i, keyEvent) -> {
                    Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    boolean focused = button.isFocused();
                    if (focused) {
                        button.setBackgroundColor(Color.parseColor("#8000BCD4"));
                    } else {
                        button.setBackgroundColor(Color.parseColor("#00000000"));
                    }
                    return false;
                }
        );
        alertDialog.setOnShowListener(dialogInterface -> {
            String getID = context.getSharedPreferences("adx", MODE_PRIVATE).getString("id", "null");
            if (!getID.equals("null")) {
                inputServer.setText(getID);
            }
        });
        return alertDialog;
    }

    public static void initWebview(WebView webView) {
        WebView.setWebContentsDebuggingEnabled(true);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView, true);
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        settings.setDatabaseEnabled(true);   //开启 database storage API 功能
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(
                new WebChromeClient()
        );
        webView.setWebViewClient(new WebViewClient());
    }


}
