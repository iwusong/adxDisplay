package com.cn.dafeng.adx.esop;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.cn.dafeng.adx.esop.utils.InitUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public AlertDialog Edit;
    protected final String IPADDRESS = "10.96.107.13";
    protected final int port = 80;
    protected final String path = "/g/c.html?id=";


    private int backNum = 0;
    private Date backTime = new Date();

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

        if (keyCode == KeyEvent.KEYCODE_VOLUME_MUTE) {
            final long timeDifference = new Date().getTime() - backTime.getTime();
            if (timeDifference < 1000) {
                backNum++;
            } else {
                backTime = new Date();
                backNum = 0;
            }
            //            执行操作
            if (backNum >= 2) {
                backTime = new Date();
                backNum = 0;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName componentName = new ComponentName("com.example.hlauncher", "com.example.hlauncher.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);
                finish();
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_DEL) {
            final long timeDifference = new Date().getTime() - backTime.getTime();
            if (timeDifference < 1000) {
                backNum++;
            } else {
                backTime = new Date();
                backNum = 0;
            }
            //            执行操作
            if (backNum >= 2) {
                backTime = new Date();
                backNum = 0;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName componentName = new ComponentName("com.charon.rocketfly", "com.charon.rocketfly.RocketActivity");
                intent.setComponent(componentName);
                startActivity(intent);
                return super.onKeyDown(keyCode, event);
            }
            return true;
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
        return "http://" + IPADDRESS + ":" + port + path + getId();
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
                        } catch (IOException e) {
                            runOnUiThread(() -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    public void goEsop() {
        if (!getId().equals("null")) {
            Intent intent = new Intent(this, WebEsop.class);
            intent.putExtra("url", getUrl());
            startActivityForResult(intent, 1);
        } else {
            runOnUiThread(() -> Edit.show());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Edit.show();
        System.out.println("result");
        super.onActivityResult(requestCode, resultCode, data);
    }
}
