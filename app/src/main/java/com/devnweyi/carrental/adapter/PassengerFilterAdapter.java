package com.devnweyi.carrental.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.databinding.ItemPassengerFilterBinding;
import com.devnweyi.carrental.model.PassengerFilterModel;
import com.devnweyi.carrental.viewmodel.ItemPassengerFilterViewModel;

import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PassengerFilterAdapter extends RecyclerView.Adapter<PassengerFilterAdapter.PassengerFilterViewHolder> {

    private List<PassengerFilterModel> lstPassengerFilter;

    public PassengerFilterAdapter() {
        this.lstPassengerFilter = Collections.emptyList();
    }

    public void setPassengerFilter(List<PassengerFilterModel> lstPassengerFilter) {
        this.lstPassengerFilter = lstPassengerFilter;
    }

    @Override
    public PassengerFilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPassengerFilterBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_passenger_filter,
                parent,
                false);
        return new PassengerFilterAdapter.PassengerFilterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PassengerFilterAdapter.PassengerFilterViewHolder holder, int position) {
        holder.bindPassengerFilter(lstPassengerFilter.get(position));
    }

    @Override
    public int getItemCount() {
        return lstPassengerFilter.size();
    }

    public static class PassengerFilterViewHolder extends RecyclerView.ViewHolder {
        final ItemPassengerFilterBinding binding;

        public PassengerFilterViewHolder(ItemPassengerFilterBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindPassengerFilter(PassengerFilterModel passengerFilterModel) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemPassengerFilterViewModel(itemView.getContext(), passengerFilterModel));
            } else {
                binding.getViewModel().setPassengerFilterModel(passengerFilterModel);
            }
        }
    }
}
