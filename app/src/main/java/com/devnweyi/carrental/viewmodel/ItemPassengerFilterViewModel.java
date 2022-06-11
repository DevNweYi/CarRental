package com.devnweyi.carrental.viewmodel;

import android.content.Context;
import android.view.View;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.model.PassengerFilterModel;

import androidx.databinding.BaseObservable;

public class ItemPassengerFilterViewModel extends BaseObservable {

    private Context context;
    private PassengerFilterModel passengerFilterModel;
    static View oldPassengerFilterView;
    static int passengerFilterValue;

    public ItemPassengerFilterViewModel(Context context, PassengerFilterModel passengerFilterModel) {
        this.context = context;
        this.passengerFilterModel = passengerFilterModel;
        passengerFilterValue=0;
    }

    public String getPassengerFilterName() {
        return passengerFilterModel.getPassengerFilterName();
    }

    public int getPassengerFilterValue() {
        return passengerFilterModel.getPassengerFilterValue();
    }

    // Allows recycling ItemPriceFilterViewModel within the recyclerview adapter
    public void setPassengerFilterModel(PassengerFilterModel passengerFilterModel) {
        this.passengerFilterModel = passengerFilterModel;
        notifyChange();
    }

    public void onPassengerFilterItemClicked(View view) {
        if (oldPassengerFilterView != null)
            oldPassengerFilterView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

        if (view != null) {
            oldPassengerFilterView = view;
            view.setBackground(context.getDrawable(R.drawable.bd_accent200_5r));
            passengerFilterValue = Integer.parseInt(view.getTag().toString());
        }
    }
}
