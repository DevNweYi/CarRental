package com.devnweyi.carrental.ui.profile;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.UserModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.library.baseAdapters.BR;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends BaseObservable {

    private Context context;
    private UserModel userModel;
    public ObservableBoolean userNameEnabled;
    public ObservableBoolean phoneNumberEnabled;
    ProfileListener profileListener;

    public ProfileViewModel(Context context, UserModel userModel,ProfileListener profileListener) {
        this.context=context;
        this.userModel=userModel;
        userNameEnabled=new ObservableBoolean(false);
        phoneNumberEnabled=new ObservableBoolean(false);
        this.profileListener=profileListener;
    }

    @Bindable
    public String getUserName(){
        return userModel.getUserName();
    }
    public void setUserName(String name){
        userModel.setUserName(name);
        notifyPropertyChanged(BR.userName);
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
    private String nameMessage=null;
    public String getNameMessage(){
        return nameMessage;
    }
    public void setNameMessage(String nameMessage){
        this.nameMessage=nameMessage;
        notifyPropertyChanged(BR.nameMessage);
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

    public void onUserNameEdit(){
        userNameEnabled.set(true);
    }

    public void onPhoneNumberEdit(){
        phoneNumberEnabled.set(true);
    }

    public void onEditProfile(){
        if(isValid()){
            updateUser(userModel);
        }
    }

    private boolean isValid(){
        if(TextUtils.isEmpty(getUserName())){
            setNameMessage("Please enter a value.");
            return false;
        }else if(TextUtils.isEmpty(getPhoneNumber())){
            setPhoneNumberMessage("Please enter a value.");
            return false;
        }
        return true;
    }

    public void onLogout(){

    }

    private void updateUser(UserModel userModel){
        (Api.getClient().editUser(userModel)).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    if (profileListener != null) profileListener.onEditUserSuccess("Success!",userModel.getUserName(),userModel.getPhoneNumber());
                }else{
                    if (profileListener != null) profileListener.onEditUserFail("Already registered with this phone number.");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("error",t.toString());
            }
        });
    }

    public interface ProfileListener{
        void onEditUserSuccess(String message,String userName,String phoneNumber);
        void onEditUserFail(String message);
    }
}