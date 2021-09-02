package com.devnweyi.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;

import com.devnweyi.carrental.databinding.ActivitySignUpBinding;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.model.UserModel;
import com.devnweyi.carrental.viewmodel.SignUpViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity implements SignUpViewModel.SignUpListener {

    ActivitySignUpBinding activitySignUpBinding;
    UserModel userModel=new UserModel();
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        activitySignUpBinding.setViewModel(new SignUpViewModel(this,userModel,this));
        activitySignUpBinding.executePendingBindings();

        activitySignUpBinding.etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int DRAWABLE_RIGHT=2;
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(event.getRawX() >= (activitySignUpBinding.etPassword.getRight() - activitySignUpBinding.etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(activitySignUpBinding.etPassword.getText().toString().length()!=0){
                            if(activitySignUpBinding.etPassword.getInputType()== InputType.TYPE_CLASS_TEXT){
                                activitySignUpBinding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            }
                            else {
                                activitySignUpBinding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    @BindingAdapter({"nameMessage"})
    public static void setNameError(TextInputLayout textInputLayout, String message){
        if(message!=null){
            textInputLayout.setError(message);
        }
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
    public void onSignUpSuccess(UserModel userModel){
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
}