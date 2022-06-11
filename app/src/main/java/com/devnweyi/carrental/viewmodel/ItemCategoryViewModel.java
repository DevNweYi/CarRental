package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.ui.searchcar.SearchCarViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemCategoryViewModel extends BaseObservable {

    private Context context;
    private CategoryModel categoryModel;
    private DataListener dataListener;

    public ItemCategoryViewModel(Context context, CategoryModel categoryModel, DataListener dataListener) {
        this.context = context;
        this.categoryModel = categoryModel;
        this.dataListener = dataListener;
    }

    public int getCategoryID() {
        return categoryModel.getCategoryId();
    }

    public String getCategoryName() {
        return categoryModel.getCategoryName();
    }

    public String getCategoryPhotoUrl() {
        return categoryModel.getCategoryPhotoUrl();
    }

    public boolean getIsSelected() {
        return categoryModel.isSelected();
    }

    // Allows recycling ItemCategoryViewModel within the recyclerview adapter
    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
        notifyChange();
    }

    public void onCategoryItemClicked() {
        getProduct(categoryModel.getCategoryId());
    }

    private void getProduct(int categoryId) {
        (Api.getClient().getProduct(categoryId)).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                SearchCarViewModel.lstProduct = response.body();
                if (SearchCarViewModel.lstProduct == null)
                    SearchCarViewModel.lstProduct = new ArrayList<>();
                if (dataListener != null)
                    dataListener.onProductByCategoryPrepared(SearchCarViewModel.lstProduct);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    @BindingAdapter("categoryPhoto")
    public static void loadCategoryPhoto(ImageView view, String categoryPhotoUrl) {
        Glide.with(view.getContext()).load(categoryPhotoUrl).apply(new RequestOptions()).into(view);
    }

    public interface DataListener {
        void onProductByCategoryPrepared(List<ProductModel> lstProduct);
    }
}
