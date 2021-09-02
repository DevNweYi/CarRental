package com.devnweyi.carrental.viewmodel;

import android.content.Context;

import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ProductRatingModel;

import androidx.databinding.BaseObservable;

public class ItemProductRatingViewModel extends BaseObservable {

    private Context context;
    private ProductRatingModel productRatingModel;

    public ItemProductRatingViewModel(Context context, ProductRatingModel productRatingModel){
        this.context=context;
        this.productRatingModel=productRatingModel;
    }

    public String getType(){
        return productRatingModel.getType();
    }

    public int getRating(){
        return productRatingModel.getRating();
    }

    // Allows recycling ItemCategoryViewModel within the recyclerview adapter
    public void setProductRatingModel(ProductRatingModel productRatingModel) {
        this.productRatingModel = productRatingModel;
        notifyChange();
    }

}
