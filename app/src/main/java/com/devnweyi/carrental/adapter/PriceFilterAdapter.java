package com.devnweyi.carrental.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.databinding.ItemPriceFilterBinding;
import com.devnweyi.carrental.model.PriceFilterModel;
import com.devnweyi.carrental.viewmodel.ItemPriceFilterViewModel;

import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PriceFilterAdapter extends RecyclerView.Adapter<PriceFilterAdapter.PriceFilterViewHolder> {

    private List<PriceFilterModel> lstPriceFilter;

    public PriceFilterAdapter() {
        this.lstPriceFilter = Collections.emptyList();
    }

    public void setPriceFilter(List<PriceFilterModel> lstPriceFilter) {
        this.lstPriceFilter = lstPriceFilter;
    }

    @Override
    public PriceFilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPriceFilterBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_price_filter,
                parent,
                false);
        return new PriceFilterAdapter.PriceFilterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PriceFilterAdapter.PriceFilterViewHolder holder, int position) {
        holder.bindPriceFilter(lstPriceFilter.get(position));
    }

    @Override
    public int getItemCount() {
        return lstPriceFilter.size();
    }

    public static class PriceFilterViewHolder extends RecyclerView.ViewHolder {
        final ItemPriceFilterBinding binding;

        public PriceFilterViewHolder(ItemPriceFilterBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindPriceFilter(PriceFilterModel priceFilterModel) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemPriceFilterViewModel(itemView.getContext(), priceFilterModel));
            } else {
                binding.getViewModel().setPriceFilterModel(priceFilterModel);
            }
        }
    }
}
