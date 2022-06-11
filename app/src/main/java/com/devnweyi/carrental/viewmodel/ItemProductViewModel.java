package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.devnweyi.carrental.CarDetailActivity;
import com.devnweyi.carrental.model.ProductModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

public class ItemProductViewModel extends BaseObservable {

    private Context context;
    private ProductModel productModel;

    public ItemProductViewModel(Context context,ProductModel productModel){
        this.context=context;
        this.productModel=productModel;
    }

    public int getProductID(){
        return productModel.getProductID();
    }

    public int getCategoryID(){
        return productModel.getCategoryID();
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

    public String getProductName(){
        return productModel.getProductName();
    }

    public String getProductDescription(){
        return productModel.getProductDescription();
    }

    public String getPricePerDay(){
        return productModel.getPricePerDay();
    }

    public String getSmallPhotoUrl(){
        return productModel.getSmallPhotoUrl();
    }

    // Allows recycling ItemProductViewModel within the recyclerview adapter
    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
        notifyChange();
    }

    public void onProductItemClicked(){
        context.startActivity(CarDetailActivity.newIntent(context,productModel));
    }

    @BindingAdapter("smallPhoto")
    public static void loadSmallPhoto(ImageView view, String smallPhotoUrl) {
        Glide.with(view.getContext()).load(smallPhotoUrl).apply(new RequestOptions()).into(view);
    }
}
