package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.devnweyi.carrental.BR;
import com.devnweyi.carrental.R;
import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.general.TimePickerDialog;
import com.devnweyi.carrental.model.BookingModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingViewModel extends BaseObservable {

    BookingModel bookingModel;
    Context context;
    DataListener dataListener;
    TimeListener timeListener;
    static boolean isPickUpTime;
    SystemSetting systemSetting=new SystemSetting();

    public BookingViewModel(Context context,BookingModel bookingModel,DataListener dataListener){
        this.context=context;
        this.bookingModel=new BookingModel(bookingModel);
        this.dataListener=dataListener;
    }

    public BookingViewModel(TimeListener timeListener){
        this.timeListener=timeListener;
    }

    public String getTripPlace(){
        return bookingModel.getTripPlace();
    }

    public void setTripPlace(String tripPlace){
        bookingModel.setTripPlace(tripPlace);
    }

    public String getPickUpAddress(){
        return bookingModel.getPickUpAddress();
    }

    public void setPickUpAddress(String pickUpAddress){
        bookingModel.setPickUpAddress(pickUpAddress);
    }

    public String getPickUpDate(){
        return bookingModel.getPickUpDate();
    }

    public void setPickUpDate(String pickUpDate){
        bookingModel.setPickUpDate(pickUpDate);
    }

    public String getPickUpTime(){
        return bookingModel.getPickUpTime();
    }

    public void setPickUpTime(String pickUpTime){
        bookingModel.setPickUpTime(pickUpTime);
    }

    public String getPickUpDay(){
        return bookingModel.getPickUpDay();
    }

    public void setPickUpDay(String pickUpDay){
        bookingModel.setPickUpDay(pickUpDay);
    }

    public String getDropOffAddress(){
        return bookingModel.getDropOffAddress();
    }

    public void setDropOffAddress(String dropOffAddress){
        bookingModel.setDropOffAddress(dropOffAddress);
    }

    public String getDropOffDate(){
        return bookingModel.getDropOffDate();
    }

    public void setDropOffDate(String dropOffDate){
        bookingModel.setDropOffDate(dropOffDate);
    }

    public String getDropOffTime(){
        return bookingModel.getDropOffTime();
    }

    public void setDropOffTime(String dropOffTime){
        bookingModel.setDropOffTime(dropOffTime);
    }

    public String getDropOffDay(){
        return bookingModel.getDropOffDay();
    }

    public void setDropOffDay(String dropOffDay){
        bookingModel.setDropOffDay(dropOffDay);
    }

    public String getProductName(){
        return bookingModel.getProductName();
    }

    public void setProductName(String productName){
        bookingModel.setProductName(productName);
    }

    public String getPricePerDay(){
        return bookingModel.getPricePerDay();
    }

    public void setPricePerDay(String pricePerDay){
        bookingModel.setPricePerDay(pricePerDay);
    }

    public String getTotalPassenger(){
        return bookingModel.getTotalPassenger();
    }

    public void setTotalPassenger(String totalPassenger){
        bookingModel.setTotalPassenger(totalPassenger);
    }

    public String getRentalDays(){
        return bookingModel.getRentalDays();
    }

    public void setRentalDays(String rentalDays){
        bookingModel.setRentalDays(rentalDays);
    }

    public String getTotalAmount(){
        return bookingModel.getTotalAmount();
    }

    public void setTotalAmount(String totalAmount){
        bookingModel.setTotalAmount(totalAmount);
    }

    @Bindable
    private String tripPlaceMessage=null;
    public String getTripPlaceMessage() {
        return tripPlaceMessage;
    }
    public void setTripPlaceMessage(String tripPlaceMessage) {
        this.tripPlaceMessage = tripPlaceMessage;
        notifyPropertyChanged(BR.tripPlaceMessage);
    }

    @Bindable
    private String totalPassengerMessage=null;
    public String getTotalPassengerMessage() {
        return totalPassengerMessage;
    }
    public void setTotalPassengerMessage(String totalPassengerMessage) {
        this.totalPassengerMessage = totalPassengerMessage;
        notifyPropertyChanged(BR.totalPassengerMessage);
    }

    @Bindable
    private String pickUpLocationMessage=null;
    public String getPickUpLocationMessage() {
        return pickUpLocationMessage;
    }
    public void setPickUpLocationMessage(String pickUpLocationMessage) {
        this.pickUpLocationMessage = pickUpLocationMessage;
        notifyPropertyChanged(BR.pickUpLocationMessage);
    }

    @Bindable
    private String dropOffLocationMessage=null;
    public String getDropOffLocationMessage() {
        return dropOffLocationMessage;
    }
    public void setDropOffLocationMessage(String dropOffLocationMessage) {
        this.dropOffLocationMessage = dropOffLocationMessage;
        notifyPropertyChanged(BR.dropOffLocationMessage);
    }

    @Bindable
    private String invalidDateMessage=null;
    public String getInvalidDateMessage() {
        return invalidDateMessage;
    }
    public void setInvalidDateMessage(String invalidDateMessage) {
        this.invalidDateMessage = invalidDateMessage;
        notifyPropertyChanged(BR.invalidDateMessage);
    }

    private void sendBooking(BookingModel bookingModel){
//        Api.getClient().sendBooking(bookingModel);
        (Api.getClient().sendBooking(bookingModel)).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("error",t.toString());
            }
        });
    }

    public void onPickUpDateClicked(){
        if(dataListener!=null)dataListener.onPickUpDateClicked();
    }

    public void onDropOffDateClicked(){
        if(dataListener!=null)dataListener.onDropOffDateClicked();
    }

    public void onPickUpTimeClicked(){
        isPickUpTime=true;
        TimePickerDialog.newInstance("",context).onCreateDialog(null).show();
    }

    public void onDropOffTimeClicked(){
        isPickUpTime=false;
        TimePickerDialog.newInstance("",context).onCreateDialog(null).show();
    }

    public void onSetTime(){
        if(timeListener!=null){
            if(isPickUpTime) timeListener.onPickUpTimeClicked();
            else timeListener.onDropOffTimeClicked();
        }
    }

    public void onCancelTime(){
        if(timeListener!=null)
            timeListener.onCloseTimePicker();
    }

    public void onBookNowClicked(){
        if(isValid()) sendBooking(bookingModel);
    }

    public void onPickUpLocationClicked(){
        if(dataListener!=null)dataListener.onMapLoaded(true);
    }

    public void onDropOffLocationClicked(){
        if(dataListener!=null)dataListener.onMapLoaded(false);
    }

    private boolean isValid(){
        if(TextUtils.isEmpty(getTripPlace())){
            setTripPlaceMessage("Please enter a value.");
            return false;
        }else if(TextUtils.isEmpty(getTotalPassenger())){
            setTotalPassengerMessage("Please enter a value.");
            return false;
        } else if(TextUtils.isEmpty(getPickUpAddress())){
            setPickUpLocationMessage("Please enter a value.");
            return false;
        }else if(TextUtils.isEmpty(getDropOffAddress())){
            setDropOffLocationMessage("Please enter a value.");
            return false;
        }
        int dayDiff=systemSetting.checkValidPickUpDropOffDate(getPickUpDate(),getDropOffDate());
        if(dayDiff <= 0){
            setInvalidDateMessage("Invalid! Pickup date is greater than drop off date.");
            return false;
        }
        return true;
    }

    public interface DataListener{
        void onPickUpDateClicked();
        void onDropOffDateClicked();
        void onMapLoaded(boolean isPickUpLocation);
    }

    public interface TimeListener{
        void onPickUpTimeClicked();
        void onDropOffTimeClicked();
        void onCloseTimePicker();
    }
}
//android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"