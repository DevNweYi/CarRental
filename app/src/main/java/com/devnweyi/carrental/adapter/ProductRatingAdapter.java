package com.devnweyi.carrental.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.databinding.ItemProductRatingBinding;
import com.devnweyi.carrental.model.ProductRatingModel;
import com.devnweyi.carrental.viewmodel.ItemProductRatingViewModel;

import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRatingAdapter extends RecyclerView.Adapter<ProductRatingAdapter.ProductRatingViewHolder> {

    private List<ProductRatingModel> lstProductRating;

    public ProductRatingAdapter() {
        this.lstProductRating = Collections.emptyList();
    }

    public void setProductRating(List<ProductRatingModel> lstProductRating) {
        this.lstProductRating = lstProductRating;
    }

    @Override
    public ProductRatingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductRatingBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_product_rating,
                parent,
                false);
        return new ProductRatingAdapter.ProductRatingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductRatingViewHolder holder, int position) {
        holder.bindProduct(lstProductRating.get(position));
    }

    @Override
    public int getItemCount() {
        return lstProductRating.size();
    }

    public static class ProductRatingViewHolder extends RecyclerView.ViewHolder {
        final ItemProductRatingBinding binding;

        public ProductRatingViewHolder(ItemProductRatingBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindProduct(ProductRatingModel productRatingModel) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemProductRatingViewModel(itemView.getContext(), productRatingModel));
            } else {
                binding.getViewModel().setProductRatingModel(productRatingModel);
            }
        }
    }
}
