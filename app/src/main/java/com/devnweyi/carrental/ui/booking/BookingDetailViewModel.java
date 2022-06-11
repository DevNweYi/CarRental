package com.devnweyi.carrental.ui.booking;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.BookingModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableInt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetailViewModel extends BaseObservable {

    private BookingModel bookingModel;
    private Context context;
    BookingDetailListener bookingDetailListener;
    public ObservableInt layoutDetailVisible;
    public ObservableInt noBookingVisible;

    public BookingDetailViewModel(Context context, BookingModel bookingModel,BookingDetailListener bookingDetailListener) {
        this.context = context;
        this.bookingModel = bookingModel;
        this.bookingDetailListener = bookingDetailListener;
        if (bookingModel.getBookingID() != 0) {
            this.layoutDetailVisible = new ObservableInt(View.VISIBLE);
            this.noBookingVisible = new ObservableInt(View.GONE);
        } else {
            this.layoutDetailVisible = new ObservableInt(View.GONE);
            this.noBookingVisible = new ObservableInt(View.VISIBLE);
        }
    }

    public String getProductName() {
        return bookingModel.getProductName();
    }

    public String getProductDescription() {
        return bookingModel.getProductDescription();
    }

    public String getBookingDateTime(){
        return bookingModel.getBookingDateTime();
    }

    public String getPricePerDay(){
        return bookingModel.getPricePerDay();
    }

    public String getTripPlace(){
        return bookingModel.getTripPlace();
    }

    public String getPickUpDate(){
        return bookingModel.getPickUpDate();
    }

    public String getPickUpTime(){
        return bookingModel.getPickUpTime();
    }

    public String getPickUpDay(){
        return bookingModel.getPickUpDay();
    }

    public String getPickUpAddress(){
        return bookingModel.getPickUpAddress();
    }

    public String getDropOffDate(){
        return bookingModel.getDropOffDate();
    }

    public String getDropOffTime(){
        return bookingModel.getDropOffTime();
    }

    public String getDropOffDay(){
        return bookingModel.getDropOffDay();
    }

    public String getDropOffAddress(){
        return bookingModel.getDropOffAddress();
    }

    public String getRentalDays(){
        return bookingModel.getRentalDays();
    }

    public String getTotalAmount(){
        return bookingModel.getTotalAmount();
    }

    public String getDetailPhotoUrl(){
        return bookingModel.getDetailPhotoUrl();
    }

    @BindingAdapter("detailPhotoInBookingDetail")
    public static void loadDetailPhoto(ImageView view, String detailPhotoUrl) {
        Glide.with(view.getContext()).load(detailPhotoUrl).apply(new RequestOptions()).into(view);
    }

    public void onBookingCancelClicked(){
        cancelBooking(bookingModel.getUserID());
    }

    private void cancelBooking(int userId){
        (Api.getClient().deleteBooking(userId)).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    if (bookingDetailListener != null) bookingDetailListener.onCancelBookingSuccess("Canceled your Booking!");
                }else{
                    if (bookingDetailListener != null) bookingDetailListener.onCancelBookingFail("Your Booking cannot be canceled!");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("error",t.toString());
            }
        });
    }

    public void onViewDriverClicked(){

    }

    public void onCarDetailClicked(){

    }

    public interface BookingDetailListener{
        void onCancelBookingSuccess(String message);
        void onCancelBookingFail(String message);
    }
}