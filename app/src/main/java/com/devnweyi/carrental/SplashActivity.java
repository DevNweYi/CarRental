package com.devnweyi.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.devnweyi.carrental.general.SystemSetting;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH=3000;
    SystemSetting systemSetting=new SystemSetting();
    Context context=this;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences=getSharedPreferences(SystemSetting.MyPREFERENCES,MODE_PRIVATE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent;
                if(systemSetting.checkConnection(context)) {
                    int userId=preferences.getInt(SystemSetting.UserID,0);
                    if(userId!=0)intent=new Intent(SplashActivity.this,MainActivity.class);
                    else intent = new Intent(SplashActivity.this, StartActivity.class);
                }
                else intent = new Intent(SplashActivity.this, NoInternetActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}