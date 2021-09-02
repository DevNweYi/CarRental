package com.devnweyi.carrental.ui.searchcar;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.devnweyi.carrental.BR;
import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableInt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCarViewModel extends BaseObservable {

    private Context context;

    public ObservableInt progressVisibility;
    public ObservableInt searchButtonVisibility;
    public ObservableInt infoMessageVisibility;

    private List<CategoryModel> lstCategory;
    private List<ProductModel> lstProduct;
    private DataListener dataListener;
    private ItemDataListener itemDataListener;
    private String searchValue;

    public SearchCarViewModel(Context context,DataListener dataListener,ItemDataListener itemDataListener) {
        this.context=context;
        this.dataListener=dataListener;
        this.itemDataListener=itemDataListener;
        progressVisibility=new ObservableInt();
        searchButtonVisibility=new ObservableInt(View.GONE);
        infoMessageVisibility=new ObservableInt(View.GONE);
        getCategory();
        getProduct();
    }

    public SearchCarViewModel(ItemDataListener itemDataListener){
        this.itemDataListener=itemDataListener;
    }

    @Bindable
    private String errorMessage=null;
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        notifyPropertyChanged(BR.errorMessage);
    }

    private void getCategory(){
        progressVisibility.set(View.VISIBLE);

        (Api.getClient().getCategory()).enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                progressVisibility.set(View.GONE);
                lstCategory = response.body();
                if(lstCategory==null) lstCategory=new ArrayList<>();
                if(dataListener != null) dataListener.onCategoryLoaded(lstCategory);
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                setErrorMessage(t.toString());
                progressVisibility.set(View.GONE);
            }
        });
    }

    private void getProduct(){
        (Api.getClient().getProduct()).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                lstProduct = response.body();
                infoMessageVisibility();
                if(dataListener != null) dataListener.onProductLoaded(lstProduct);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                setErrorMessage(t.toString());
            }
        });
    }

    private void getProduct(String searchValue){
        (Api.getClient().getProduct(searchValue)).enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                lstProduct = response.body();
                infoMessageVisibility();
                if(dataListener != null) dataListener.onProductLoaded(lstProduct);
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                setErrorMessage(t.toString());
            }
        });
    }

    private void infoMessageVisibility(){
        if(lstProduct==null) {
            lstProduct=new ArrayList<>();
            infoMessageVisibility.set(View.VISIBLE);
        }else{
            infoMessageVisibility.set(View.GONE);
        }
    }

    public void getProductByCategory(List<ProductModel> lstProduct){
        if(itemDataListener!=null)itemDataListener.onProductByCategoryLoaded(lstProduct);
    }

    public boolean onSearchAction(TextView view, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String value = view.getText().toString();
            if (value.length() > 0) getProduct(value);
            return true;
        }
        return false;
    }

    public void onClickSearch(View view){
        getProduct(searchValue);
    }

    public TextWatcher getSearchEditTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                searchValue = charSequence.toString();
                searchButtonVisibility.set(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public interface DataListener {
        void onCategoryLoaded(List<CategoryModel> lstCategory);
        void onProductLoaded(List<ProductModel> lstProduct);
    }

    public interface ItemDataListener{
        void onProductByCategoryLoaded(List<ProductModel> lstProduct);
    }
}