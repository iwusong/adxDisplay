package com.cn.dafeng.adx.esop.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import com.cn.dafeng.adx.esop.R;

import static android.content.Context.MODE_PRIVATE;

public class InitEditUtil {
    public static AlertDialog init(Context context) {
        final EditText inputServer = new EditText(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("输入设备编码").setView(inputServer)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确定", (dialog, which) -> {
            SharedPreferences adx = context.getSharedPreferences("adx", MODE_PRIVATE);
            adx.edit().putString("url", inputServer.getText().toString()).apply();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(dialog -> {
            Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//
        });
        return alertDialog;
    }
}
