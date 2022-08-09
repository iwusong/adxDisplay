package com.cn.dafeng.adx.esop;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cn.dafeng.adx.esop.utils.InitUtil;

import java.io.IOException;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {


    AlertDialog Edit;


    protected final String IPADDRESS = "192.168.0.100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "当前的id为" + getId(), Toast.LENGTH_SHORT).show();

        testInternet();
        Edit = InitUtil.initAlertDialog(this);
        if (getId().equals("null")) {
            Edit.show();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU | keyCode == KeyEvent.KEYCODE_CTRL_LEFT) {
            Edit.show();
        }
        return super.onKeyDown(keyCode, event);
    }


    public String getId() {
        SharedPreferences adx = MainActivity.this.getSharedPreferences("adx", MODE_PRIVATE);
        String id = adx.getString("id", "null");
        if (id.trim().equals("")) {
            return "null";
        }
        return id;
    }

    public String getUrl() {
        return "http://" + IPADDRESS + "/g/c.html?id=" + getId();
    }

    protected void testInternet() {
        new Thread(
                () -> {
                    while (true) {
                        try {
                            InetAddress host = InetAddress.getByName(IPADDRESS);
                            boolean reachable = host.isReachable(3000);
                            if (reachable) {
                                runOnUiThread(() -> {
                                    Toast.makeText(this, "服务器已连接", Toast.LENGTH_SHORT).show();
                                    goEsop();
                                });
                                break;
                            } else {
                                runOnUiThread(() -> Toast.makeText(this, "服务器未连接", Toast.LENGTH_SHORT).show());
                            }
                            Thread.sleep(3000);
                        } catch (IOException | InterruptedException e) {
                            runOnUiThread(() -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
                            break;
                        }
                    }


                }
        ).start();
    }

    public void goEsop() {
        if (!getId().equals("null")) {
            Intent intent = new Intent(this, WebEsop.class);
            intent.putExtra("url", getUrl());
            startActivity(intent);
        } else {
            runOnUiThread(() -> Edit.show());
        }
    }
}
