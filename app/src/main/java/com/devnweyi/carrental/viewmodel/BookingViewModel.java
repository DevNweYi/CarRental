package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.devnweyi.carrental.BR;
import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.general.TimePickerDialog;
import com.devnweyi.carrental.model.BookingModel;
import com.devnweyi.carrental.model.DriverModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
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
    List<DriverModel> lstDriver=new ArrayList<>();

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

    public String getDetailPhotoUrl(){
        return bookingModel.getDetailPhotoUrl();
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
        (Api.getClient().sendBooking(bookingModel)).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    getDriver(bookingModel.getProductID());
                }else{
                    if(dataListener!=null)dataListener.onBookingFail();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("error",t.toString());
            }
        });
    }

    private void getDriver(int productId){
        (Api.getClient().getDriver(productId)).enqueue(new Callback<List<DriverModel>>() {
            @Override
            public void onResponse(Call<List<DriverModel>> call, Response<List<DriverModel>> response) {
                lstDriver = response.body();
                if(lstDriver==null)lstDriver=new ArrayList<>();
                if(dataListener!=null)dataListener.onBookingSuccess(lstDriver.get(0));
            }

            @Override
            public void onFailure(Call<List<DriverModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
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

    @BindingAdapter("detailPhotoInBooking")
    public static void loadDetailPhotoInBooking(ImageView view, String detailPhotoUrl) {
        Glide.with(view.getContext()).load(detailPhotoUrl).apply(new RequestOptions()).into(view);
    }

    public interface DataListener{
        void onBookingSuccess(DriverModel driverModel);
        void onPickUpDateClicked();
        void onDropOffDateClicked();
        void onMapLoaded(boolean isPickUpLocation);
        void onBookingFail();
    }

    public interface TimeListener{
        void onPickUpTimeClicked();
        void onDropOffTimeClicked();
        void onCloseTimePicker();
    }
}
//android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"android:onClick="@{()->viewModel.onPickUpLocationClicked()}"