package com.devnweyi.carrental.viewmodel;

import android.content.Context;

import com.devnweyi.carrental.CarDetailActivity;
import com.devnweyi.carrental.model.ProductModel;

import androidx.databinding.BaseObservable;

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

    // Allows recycling ItemProductViewModel within the recyclerview adapter
    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
        notifyChange();
    }

    public void onProductItemClicked(){
        context.startActivity(CarDetailActivity.newIntent(context,productModel));
    }
}
