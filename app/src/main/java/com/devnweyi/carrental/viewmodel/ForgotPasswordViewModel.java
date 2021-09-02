package com.devnweyi.carrental.viewmodel;

import android.text.TextUtils;

import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.UserModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordViewModel extends BaseObservable {

    UserModel userModel;
    ForgotPasswordListener forgotPasswordListener;

    public ForgotPasswordViewModel(ForgotPasswordListener forgotPasswordListener,UserModel userModel){
        this.forgotPasswordListener=forgotPasswordListener;
        this.userModel=userModel;
    }

    @Bindable
    public String getPhoneNumber(){
        return userModel.getPhoneNumber();
    }
    public void setPhoneNumber(String phoneNumber){
        userModel.setPhoneNumber(phoneNumber);
        notifyPropertyChanged(BR.phoneNumber);
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

    private boolean isValid(){
        if(TextUtils.isEmpty(getPhoneNumber())){
            setPhoneNumberMessage("Please enter a value.");
            return false;
        }
        return true;
    }

    private void sendPhone(String phoneNumber){
        (Api.getClient().forgotPassword(phoneNumber)).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    if (forgotPasswordListener != null) forgotPasswordListener.onSendSuccess();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                if(forgotPasswordListener!=null)forgotPasswordListener.onNetError(t.toString());
            }
        });
    }

    public void onSendClicked(){
        if(isValid())sendPhone(getPhoneNumber());
    }

    public void onBackClicked(){
        if(forgotPasswordListener!=null)forgotPasswordListener.onBack();
    }

    public interface ForgotPasswordListener{
        void onSendSuccess();
        void onBack();
        void onNetError(String error);
    }
}
