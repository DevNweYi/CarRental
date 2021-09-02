package com.devnweyi.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.devnweyi.carrental.databinding.ActivityStartBinding;
import com.devnweyi.carrental.viewmodel.StartViewModel;

public class StartActivity extends AppCompatActivity implements StartViewModel.EventListener {

    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_start);
        binding.setViewModel(new StartViewModel(this));
    }

    @Override
    public void onSignUpClicked(){
        Intent i=new Intent(StartActivity.this,SignUpActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onSignInClicked(){
        Intent i=new Intent(StartActivity.this,SignInActivity.class);
        startActivity(i);
        finish();
    }
}