package com.cn.dafeng.adx.esop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.cn.dafeng.adx.esop.utils.InitEditUtil;

public class MainActivity extends AppCompatActivity {


    AlertDialog Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Edit = InitEditUtil.init(this);

        if (getUrl().equals("null")) {
            Edit.show();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        System.out.println(keyCode);
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            Edit.show();
        }

        return super.onKeyDown(keyCode, event);
    }


    protected String getUrl() {
        SharedPreferences adx = MainActivity.this.getSharedPreferences("adx", MODE_PRIVATE);
        return adx.getString("url", "null");
    }


}
