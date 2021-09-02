package com.devnweyi.carrental.ui.searchcar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.adapter.CategoryAdapter;
import com.devnweyi.carrental.adapter.ProductAdapter;
import com.devnweyi.carrental.databinding.FragmentSearchCarBinding;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ProductModel;

import java.util.List;

public class SearchCarFragment extends Fragment implements SearchCarViewModel.DataListener,SearchCarViewModel.ItemDataListener {

    public FragmentSearchCarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchCarBinding.inflate(inflater, container, false);
        binding.setViewModel(new SearchCarViewModel(getContext(),this,this));
        binding.executePendingBindings();
        View root = binding.getRoot();
        setHasOptionsMenu(true);
        setupRecyclerView();
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        inflater.inflate(R.menu.menu_search_car,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_filter:
                Toast.makeText(getContext(),"Filter",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_sort:
                Toast.makeText(getContext(),"Sort",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_refresh:
                binding.etSearch.setText("");
                binding.layoutNoResult.setVisibility(View.GONE);
                new SearchCarViewModel(getContext(),this,this);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @BindingAdapter({"errorMessage"})
    public static void setError(View view,String message){
        if(message!=null){
            Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCategoryLoaded(List<CategoryModel> lstCategory) {
        CategoryAdapter adapter = (CategoryAdapter) binding.rvCategory.getAdapter();
        adapter.setCategory(lstCategory);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProductLoaded(List<ProductModel> lstProduct) {
        ProductAdapter adapter = (ProductAdapter) binding.rvProduct.getAdapter();
        adapter.setProduct(lstProduct);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProductByCategoryLoaded(List<ProductModel> lstProduct){
        binding.etSearch.setText("");
        if(lstProduct.size()==0)binding.layoutNoResult.setVisibility(View.VISIBLE);
        else binding.layoutNoResult.setVisibility(View.GONE);
        ProductAdapter adapter = (ProductAdapter) binding.rvProduct.getAdapter();
        adapter.setProduct(lstProduct);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);
        binding.rvCategory.setAdapter(categoryAdapter);
        ProductAdapter productAdapter=new ProductAdapter();
        binding.rvProduct.setAdapter(productAdapter);
        binding.rvProduct.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}