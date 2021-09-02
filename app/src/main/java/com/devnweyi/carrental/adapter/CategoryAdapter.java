package com.devnweyi.carrental.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.databinding.ItemCategoryBinding;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.ui.searchcar.SearchCarViewModel;
import com.devnweyi.carrental.viewmodel.ItemCategoryViewModel;

import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<CategoryModel> lstCategory;
    SearchCarViewModel.ItemDataListener itemDataListener;

    public CategoryAdapter(SearchCarViewModel.ItemDataListener itemDataListener) {
        this.lstCategory = Collections.emptyList();
        this.itemDataListener=itemDataListener;
    }

    public void setCategory(List<CategoryModel> lstCategory) {
        this.lstCategory = lstCategory;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_category,
                parent,
                false);
        return new CategoryViewHolder(binding,itemDataListener);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bindCategory(lstCategory.get(position));
    }

    @Override
    public int getItemCount() {
        return lstCategory.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder implements ItemCategoryViewModel.DataListener {
        final ItemCategoryBinding binding;
        SearchCarViewModel.ItemDataListener itemDataListener;

        public CategoryViewHolder(ItemCategoryBinding binding,SearchCarViewModel.ItemDataListener itemDataListener) {
            super(binding.cardView);
            this.binding = binding;
            this.itemDataListener=itemDataListener;
        }

        void bindCategory(CategoryModel categoryModel) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemCategoryViewModel(itemView.getContext(), categoryModel,this));
            } else {
                binding.getViewModel().setCategoryModel(categoryModel);
            }
        }

        @Override
        public void onProductByCategoryPrepared(List<ProductModel> lstProduct) {
            SearchCarViewModel searchCarViewModel=new SearchCarViewModel(itemDataListener);
            searchCarViewModel.getProductByCategory(lstProduct);
        }
    }
}
