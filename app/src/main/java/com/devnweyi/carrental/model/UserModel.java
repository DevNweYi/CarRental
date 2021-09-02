package com.devnweyi.carrental.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class UserModel {

    @Nullable
    @SerializedName("UserID")
    int userId;
    @SerializedName("UserName")
    String userName;
    @SerializedName("PhoneNumber")
    String phoneNumber;
    @SerializedName("Password")
    String password;


   /* public UserModel(String name,String phoneNumber,String password){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.password=password;
    }*/

    public UserModel(){

    }

    public UserModel(UserModel userModel){
        this.userId=userModel.userId;
        this.userName =userModel.userName;
        this.phoneNumber=userModel.phoneNumber;
        this.password=userModel.password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Nullable
    public String getUserName() {
        return userName;
    }

    public void setUserName(@Nullable String userName) {
        this.userName = userName;
    }

    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nullable String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }
}
