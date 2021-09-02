package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.util.Log;

import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemCategoryViewModel extends BaseObservable {

    private Context context;
    private CategoryModel categoryModel;
    private List<ProductModel> lstProduct;
    private DataListener dataListener;

    public ItemCategoryViewModel(Context context,CategoryModel categoryModel,DataListener dataListener){
        this.context=context;
        this.categoryModel=categoryModel;
        this.dataListener=dataListener;
    }

    public int getCategoryID(){
        return categoryModel.getCategoryId();
    }

    public String getCategoryName(){
        return categoryModel.getCategoryName();
    }

    public boolean getIsSelected(){
        return categoryModel.isSelected();
    }

    /*@Bindable
    private boolean isSelectCategory=false;
    public boolean getIsSelectCategory() {
        return isSelectCategory;
    }
    public void setIsSelectCategory(boolean isSelectCategory) {
        this.isSelectCategory = isSelectCategory;
        notifyPropertyChanged(BR.isSelectCategory);
    }*/

    // Allows recycling ItemCategoryViewModel within the recyclerview adapter
    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
        notifyChange();
    }

    public void onCategoryItemClicked(){
        getProduct(categoryModel.getCategoryId());
    }

    private void getProduct(int categoryId){
        (Api.getClient().getProduct(categoryId)).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                lstProduct = response.body();
                if(lstProduct==null)lstProduct=new ArrayList<>();
                if(dataListener != null) dataListener.onProductByCategoryPrepared(lstProduct);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Log.e("error",t.toString());
                //setErrorMessage(t.toString());
            }
        });
    }

    public interface DataListener {
        void onProductByCategoryPrepared(List<ProductModel> lstProduct);
    }
}
