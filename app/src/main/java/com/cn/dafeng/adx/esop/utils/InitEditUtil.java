package com.cn.dafeng.adx.esop.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;

import static android.content.Context.MODE_PRIVATE;

public class InitEditUtil {
    public static AlertDialog init(Context context) {
        final EditText inputServer = new EditText(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("输入设备编码").setView(inputServer);
        builder.setPositiveButton("确定", (dialog, which) -> {
            SharedPreferences adx = context.getSharedPreferences("adx", MODE_PRIVATE);
            adx.edit().putString("url", inputServer.getText().toString()).apply();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnKeyListener(
                (dialogInterface, i, keyEvent) -> {
                    Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    boolean focused = button.isFocused();
                    if (focused){
                        button.setBackgroundColor(Color.parseColor("#8000BCD4"));
                    }else {
                        button.setBackgroundColor(Color.parseColor("#00000000"));
                    }
                    return false;
                }
        );
        return alertDialog;
    }
}
