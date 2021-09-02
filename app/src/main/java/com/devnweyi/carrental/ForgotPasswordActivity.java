package com.devnweyi.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.devnweyi.carrental.databinding.ActivityForgotPasswordBinding;
import com.devnweyi.carrental.model.UserModel;
import com.devnweyi.carrental.viewmodel.ForgotPasswordViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordViewModel.ForgotPasswordListener {

    ActivityForgotPasswordBinding binding;
    UserModel userModel=new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        binding.setViewModel(new ForgotPasswordViewModel(this,userModel));
    }

    @BindingAdapter({"phoneNumberMessage"})
    public static void setPhoneNumberError(TextInputLayout textInputLayout, String message){
        if(message!=null){
            textInputLayout.setError(message);
        }
    }

    @Override
    public void onSendSuccess(){

    }

    @Override
    public void onBack(){
        finish();
    }

    @Override
    public void onNetError(String error){
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }
}