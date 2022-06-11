package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.view.View;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.model.PriceFilterModel;

import androidx.databinding.BaseObservable;

public class ItemPriceFilterViewModel extends BaseObservable {

    private Context context;
    private PriceFilterModel priceFilterModel;
    static View oldPriceFilterView;
    static String priceFilterValue;

    public ItemPriceFilterViewModel(Context context, PriceFilterModel priceFilterModel) {
        this.context = context;
        this.priceFilterModel = priceFilterModel;
        priceFilterValue="";
    }

    public String getPriceFilterName() {
        return priceFilterModel.getPriceFilterName();
    }

    public String getPriceFilterValue(){
        return priceFilterModel.getPriceFilterValue();
    }

    // Allows recycling ItemPriceFilterViewModel within the recyclerview adapter
    public void setPriceFilterModel(PriceFilterModel priceFilterModel) {
        this.priceFilterModel = priceFilterModel;
        notifyChange();
    }

    public void onPriceFilterItemClicked(View view) {
        if (oldPriceFilterView != null)
            oldPriceFilterView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

        if (view != null) {
            oldPriceFilterView = view;
            view.setBackground(context.getDrawable(R.drawable.bd_accent200_5r));
            priceFilterValue = view.getTag().toString();
        }
    }
}
