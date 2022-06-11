package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.util.Log;

import com.devnweyi.carrental.api.Api;
import com.devnweyi.carrental.model.PassengerFilterModel;
import com.devnweyi.carrental.model.PriceFilterModel;
import com.devnweyi.carrental.ui.searchcar.SearchCarFragment;
import com.devnweyi.carrental.ui.searchcar.SearchCarViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.BaseObservable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterViewModel extends BaseObservable {

    private Context context;
    private List<PriceFilterModel> lstPriceFilter;
    private List<PassengerFilterModel> lstPassengerFilter;
    private DataListener dataListener;

    public FilterViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
        getPassengerFilter();
        getPriceFilter();
    }

    private void getPriceFilter() {
        (Api.getClient().getPriceFilter()).enqueue(new Callback<List<PriceFilterModel>>() {
            @Override
            public void onResponse(Call<List<PriceFilterModel>> call, Response<List<PriceFilterModel>> response) {
                lstPriceFilter = response.body();
                if (lstPriceFilter == null) lstPriceFilter = new ArrayList<>();
                if (dataListener != null) dataListener.onPriceFilterLoaded(lstPriceFilter);
            }

            @Override
            public void onFailure(Call<List<PriceFilterModel>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    private void getPassengerFilter() {
        (Api.getClient().getPassengerFilter()).enqueue(new Callback<List<PassengerFilterModel>>() {
            @Override
            public void onResponse(Call<List<PassengerFilterModel>> call, Response<List<PassengerFilterModel>> response) {
                lstPassengerFilter = response.body();
                if (lstPassengerFilter == null) lstPassengerFilter = new ArrayList<>();
                if (dataListener != null) dataListener.onPassengerFilterLoaded(lstPassengerFilter);
            }

            @Override
            public void onFailure(Call<List<PassengerFilterModel>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    public void onFilterClicked(){
        if (dataListener != null) dataListener.onFinished();
        new SearchCarViewModel(new SearchCarFragment(),ItemPassengerFilterViewModel.passengerFilterValue,ItemPriceFilterViewModel.priceFilterValue);
    }

    public interface DataListener {
        void onPriceFilterLoaded(List<PriceFilterModel> lstPriceFilter);
        void onPassengerFilterLoaded(List<PassengerFilterModel> lstPassengerFilter);
        void onFinished();
    }
}

