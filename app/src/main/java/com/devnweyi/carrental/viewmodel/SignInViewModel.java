package com.devnweyi.carrental.viewmodel;

import android.text.TextUtils;

import com.devnweyi.carrental.BR;
import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends BaseObservable {

    UserModel userModel;
    List<UserModel> lstUser=new ArrayList<>();
    SignInListener signInListener;

    public SignInViewModel(SignInListener signInListener,UserModel userModel){
        this.signInListener=signInListener;
        this.userModel=userModel;
    }

    @Bindable
    public String getPhoneNumber(){
        return userModel.getPhoneNumber();
    }
    public void setPhoneNumber(String phoneNumber){
        userModel.setPhoneNumber(phoneNumber);
    }

    @Bindable
    private String phoneNumberMessage=null;
    public String getPhoneNumberMessage() {
        return phoneNumberMessage;
    }
    public void setPhoneNumberMessage(String phoneNumberMessage) {
        this.phoneNumberMessage = phoneNumberMessage;
        notifyPropertyChanged(BR.phoneNumberMessage);
    }

    @Bindable
    public String getPassword(){
        return userModel.getPassword();
    }
    public void setPassword(String password){
        userModel.setPassword(password);
    }

    @Bindable
    private String passwordMessage=null;
    public String getPasswordMessage() {
        return passwordMessage;
    }
    public void setPasswordMessage(String passwordMessage) {
        this.passwordMessage = passwordMessage;
        notifyPropertyChanged(BR.passwordMessage);
    }

    private boolean isValid(){
        if(TextUtils.isEmpty(getPhoneNumber())){
            setPhoneNumberMessage("Please enter a value.");
            return false;
        }else if(TextUtils.isEmpty(getPassword())){
            setPasswordMessage("Please enter a value.");
            return false;
        }
        return true;
    }

    private void signInUser(String phoneNumber,String password){
        (Api.getClient().signInUser(phoneNumber,password)).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if(response.isSuccessful()) {
                    lstUser = response.body();
                    if (signInListener != null) signInListener.onSignInSuccess(lstUser.get(0));
                }else{
                    if(signInListener!=null)signInListener.onSignInFail("Invalid phone number or password.");
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                if(signInListener!=null)signInListener.onSignInNetError(t.toString());
            }
        });
    }

    public void onSignInClicked(){
        if(isValid())signInUser(getPhoneNumber(),getPassword());
    }

    public void onForgotPasswordClicked(){
        if(signInListener!=null)signInListener.onForgotPassword();
    }

    public interface SignInListener{
        void onSignInSuccess(UserModel userModel);
        void onSignInFail(String message);
        void onSignInNetError(String error);
        void onForgotPassword();
    }
}
