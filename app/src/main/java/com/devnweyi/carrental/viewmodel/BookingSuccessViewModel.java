package com.devnweyi.carrental.viewmodel;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.devnweyi.carrental.model.DriverModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

public class BookingSuccessViewModel extends BaseObservable {

    DriverModel driverModel;
    EventListener eventListener;

    public BookingSuccessViewModel(DriverModel driverModel,EventListener eventListener){
        this.driverModel=driverModel;
        this.eventListener=eventListener;
    }

    public String getDriverName(){
        return driverModel.getDriverName();
    }
    public String getPhoneNumber(){
        return driverModel.getPhoneNumber();
    }
    public String getCarNumber(){
        return driverModel.getCarNumber();
    }
    public String getAge(){
        return driverModel.getAge();
    }
    public int getRating(){
        return driverModel.getRating();
    }
    public String getPhotoUrl(){
        return driverModel.getPhotoUrl();
    }

    public void onCallHimClicked(){
        if(eventListener!=null)eventListener.onCallHimClicked(getPhoneNumber());
    }

    public void onHomeClicked(){
        if(eventListener!=null)eventListener.onHomeClicked();
    }

    public void onSeeBookingClicked(){
        if(eventListener!=null)eventListener.onSeeBookingClicked();
    }

    @BindingAdapter("driverPhoto")
    public static void loadDriverPhoto(ImageView view, String driverPhotoUrl) {
        Glide.with(view.getContext()).load(driverPhotoUrl).apply(new RequestOptions()).into(view);
    }

    public interface EventListener{
        void onCallHimClicked(String phoneNumber);
        void onHomeClicked();
        void onSeeBookingClicked();
    }

}
