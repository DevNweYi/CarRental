package com.devnweyi.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.devnweyi.carrental.databinding.ActivityNoInternetBinding;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.viewmodel.NoInternetViewModel;

public class NoInternetActivity extends AppCompatActivity implements NoInternetViewModel.EventListener {

    ActivityNoInternetBinding binding;
    SystemSetting systemSetting=new SystemSetting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_no_internet);
        binding.setViewModel(new NoInternetViewModel(this));
    }

    @Override
    public void onCloseClicked(){
        finish();
    }

    @Override
    public void onRetryClicked(){
        if(systemSetting.checkConnection(this)){
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
    }
}