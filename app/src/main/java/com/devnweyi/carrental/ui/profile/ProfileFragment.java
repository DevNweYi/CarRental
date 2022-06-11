package com.devnweyi.carrental.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

import com.devnweyi.carrental.databinding.FragmentProfileBinding;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.model.UserModel;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileFragment extends Fragment implements ProfileViewModel.ProfileListener {

    private FragmentProfileBinding binding;
    SharedPreferences sharedpreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        UserModel userModel=new UserModel();
        sharedpreferences= getContext().getSharedPreferences(SystemSetting.MyPREFERENCES, Context.MODE_PRIVATE);
        userModel.setUserId(sharedpreferences.getInt(SystemSetting.UserID,0));
        userModel.setUserName(sharedpreferences.getString(SystemSetting.UserName,""));
        userModel.setPhoneNumber(sharedpreferences.getString(SystemSetting.PhoneNumber,""));
        binding.setViewModel(new ProfileViewModel(getContext(),userModel,this));
        binding.executePendingBindings();
        View root = binding.getRoot();

        return root;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onEditUserSuccess(String message,String userName,String phoneNumber) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SystemSetting.UserName, userName);
        editor.putString(SystemSetting.PhoneNumber, phoneNumber);
        editor.commit();
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEditUserFail(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }
}