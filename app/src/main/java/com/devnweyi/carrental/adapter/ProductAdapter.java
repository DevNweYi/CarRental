package com.devnweyi.carrental.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.databinding.ItemProductBinding;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.viewmodel.ItemProductViewModel;

import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> lstProduct;

    public ProductAdapter() {
        this.lstProduct = Collections.emptyList();
    }

    public void setProduct(List<ProductModel> lstProduct) {
        this.lstProduct = lstProduct;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_product,
                parent,
                false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bindProduct(lstProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        final ItemProductBinding binding;

        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindProduct(ProductModel productModel) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemProductViewModel(itemView.getContext(), productModel));
            } else {
                binding.getViewModel().setProductModel(productModel);
            }
        }
    }
}
