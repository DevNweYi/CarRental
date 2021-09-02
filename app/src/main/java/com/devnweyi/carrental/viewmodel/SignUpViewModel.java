package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

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

public class SignUpViewModel extends BaseObservable {

    UserModel userModel;
    List<UserModel> lstUserModel=new ArrayList<>();
    private Context context;
    SignUpListener signUpListener;

    public SignUpViewModel(Context context,UserModel userModel,SignUpListener signUpListener){
        this.context=context;
        this.userModel=new UserModel(userModel);
        this.signUpListener=signUpListener;
    }

    @Bindable
    private String nameMessage=null;
    public String getNameMessage() {
        return nameMessage;
    }
    public void setNameMessage(String nameMessage) {
        this.nameMessage = nameMessage;
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

    @Bindable
    private String passwordMessage=null;
    public String getPasswordMessage() {
        return passwordMessage;
    }
    public void setPasswordMessage(String passwordMessage) {
        this.passwordMessage = passwordMessage;
        notifyPropertyChanged(BR.passwordMessage);
    }

    @Bindable
    public int getUserID(){
        return userModel.getUserId();
    }
    public void setUserID(int userId){
        userModel.setUserId(userId);
        notifyPropertyChanged(BR.userID);
    }

    @Bindable
    public String getName(){
        return userModel.getUserName();
    }
    public void setName(String name){
        userModel.setUserName(name);
        notifyPropertyChanged(BR.name);
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
    public String getPassword(){
        return userModel.getPassword();
    }
    public void setPassword(String password){
        userModel.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    private void sendUser(UserModel userModel){
        (Api.getClient().sendUser(userModel)).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if(response.isSuccessful()) {
                    lstUserModel = response.body();
                    if (signUpListener != null) signUpListener.onSignUpSuccess(lstUserModel.get(0));
                }else{
                    Toast.makeText(context,"Already exist user with this phone number.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.e("error",t.toString());
            }
        });
    }

    public void onSignUpClicked(){
        if(isValid())sendUser(userModel);
    }

    private boolean isValid(){
        if(TextUtils.isEmpty(getName())){
            setNameMessage("Please enter a value.");
            return false;
        }else if(TextUtils.isEmpty(getPhoneNumber())){
            setPhoneNumberMessage("Please enter a value.");
            return false;
        }else if(TextUtils.isEmpty(getPassword())){
            setPasswordMessage("Please enter a value.");
            return false;
        }else if(getPassword().length() <= 5){
            setPasswordMessage("Password length must be greater than 5.");
            return false;
        }
        return true;
    }

    public interface SignUpListener{
        void onSignUpSuccess(UserModel userModel);
    }
}
