package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.util.Log;

import com.devnweyi.carrental.BookingActivity;
import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.model.ProductRatingModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarDetailViewModel extends BaseObservable {

    private Context context;
    private ProductModel productModel;
    private List<ProductRatingModel> lstProductRating;
    private DataListener dataListener;

    public CarDetailViewModel(Context context,ProductModel productModel,DataListener dataListener){
        this.context=context;
        this.productModel=productModel;
        this.dataListener=dataListener;
        getProductRating(productModel.getProductID());
    }

    public int getProductID(){
        return productModel.getProductID();
    }

    public String getProductName(){
        return productModel.getProductName();
    }

    public String getProductDescription(){
        return productModel.getProductDescription();
    }

    public int getTotalWindow(){
        return productModel.getTotalWindow();
    }

    public int getAllowPassengerNo(){
        return productModel.getAllowPassengerNo();
    }

    public int getAllowPassengerBag(){
        return productModel.getAllowPassengerBag();
    }

    public int getTotalSeat(){
        return productModel.getTotalSeat();
    }

    public String getPricePerDay(){
        return productModel.getPricePerDay();
    }

    public String getSpeedPerHour(){
        return productModel.getSpeedPerHour();
    }

    public String getGearSystem(){
        return productModel.getGearSystem();
    }

    public String getCylinder(){
        return productModel.getCylinder();
    }

    public String getFuelType(){
        return productModel.getFuelType();
    }

    public boolean getIsAircon(){
        return productModel.isAircon();
    }

    private void getProductRating(int productId){
        (Api.getClient().getProductRating(productId)).enqueue(new Callback<List<ProductRatingModel>>() {
            @Override
            public void onResponse(Call<List<ProductRatingModel>> call, Response<List<ProductRatingModel>> response) {
                lstProductRating = response.body();
                if(lstProductRating==null)lstProductRating=new ArrayList<>();
                if(dataListener != null) dataListener.onProductRatingLoaded(lstProductRating);
            }

            @Override
            public void onFailure(Call<List<ProductRatingModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.e("error",t.toString());
                //setErrorMessage(t.toString());
            }
        });
    }

    public void onBookingClicked(){
        context.startActivity(BookingActivity.newIntent(context,productModel));
    }

    public interface DataListener {
        void onProductRatingLoaded(List<ProductRatingModel> lstProductRating);
    }
}
