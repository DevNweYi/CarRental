package com.devnweyi.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.devnweyi.carrental.databinding.ActivitySignInBinding;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.model.UserModel;
import com.devnweyi.carrental.viewmodel.SignInViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class SignInActivity extends AppCompatActivity implements SignInViewModel.SignInListener {

    SharedPreferences sharedpreferences;
    ActivitySignInBinding binding;
    UserModel userModel=new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_in);
        binding.setViewModel(new SignInViewModel(this,userModel));

        binding.etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int DRAWABLE_RIGHT=2;
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(event.getRawX() >= (binding.etPassword.getRight() - binding.etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(binding.etPassword.getText().toString().length()!=0){
                            if(binding.etPassword.getInputType()== InputType.TYPE_CLASS_TEXT){
                                binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            }
                            else {
                                binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onSignInSuccess(UserModel userModel){
        sharedpreferences = getSharedPreferences(SystemSetting.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(SystemSetting.UserID,userModel.getUserId());
        editor.putString(SystemSetting.UserName, userModel.getUserName());
        editor.putString(SystemSetting.Password, userModel.getPassword());
        editor.putString(SystemSetting.PhoneNumber, userModel.getPhoneNumber());
        editor.commit();
        startActivity(MainActivity.newIntent(this));
        finish();
    }

    @BindingAdapter({"phoneNumberMessage"})
    public static void setPhoneNumberError(TextInputLayout textInputLayout, String message){
        if(message!=null){
            textInputLayout.setError(message);
        }
    }

    @BindingAdapter({"passwordMessage"})
    public static void setPasswordError(TextInputLayout textInputLayout, String message){
        if(message!=null){
            textInputLayout.setError(message);
        }
    }

    @Override
    public void onSignInFail(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInNetError(String error){
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onForgotPassword(){
        Intent i=new Intent(this,ForgotPasswordActivity.class);
        startActivity(i);
    }
}