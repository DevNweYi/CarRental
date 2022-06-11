package com.devnweyi.carrental.ui.searchcar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devnweyi.carrental.FilterActivity;
import com.devnweyi.carrental.R;
import com.devnweyi.carrental.adapter.CategoryAdapter;
import com.devnweyi.carrental.adapter.ProductAdapter;
import com.devnweyi.carrental.databinding.FragmentSearchCarBinding;
import com.devnweyi.carrental.general.ConnectionLiveData;
import com.devnweyi.carrental.general.SortDialog;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.model.CategoryModel;
import com.devnweyi.carrental.model.ConnectionModel;
import com.devnweyi.carrental.model.ProductModel;

import java.util.List;

public class SearchCarFragment extends Fragment implements SearchCarViewModel.DataListener,SearchCarViewModel.ItemDataListener {

    public static FragmentSearchCarBinding binding;
    SystemSetting systemSetting=new SystemSetting();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchCarBinding.inflate(inflater, container, false);
        binding.setViewModel(new SearchCarViewModel(getContext(),this,this));
        binding.executePendingBindings();
        View root = binding.getRoot();
        setHasOptionsMenu(true);
        setupRecyclerView();

        ConnectionLiveData connectionLiveData=new ConnectionLiveData(getContext());
        connectionLiveData.observe(getViewLifecycleOwner(), new Observer<ConnectionModel>() {
            @Override
            public void onChanged(ConnectionModel connectionModel) {
                if (!connectionModel.getIsConnected()) systemSetting.showSnackBar(root);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
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
                Intent i=new Intent(getContext(), FilterActivity.class);
                startActivity(i);
                return true;
            case R.id.action_sort:
                SortDialog.newInstance("",getContext()).onCreateDialog(null).show();
                return true;
            case R.id.action_refresh:
                actionRefresh();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
        if (binding != null && lstProduct != null) {
            ProductAdapter adapter = (ProductAdapter) binding.rvProduct.getAdapter();
            adapter.setProduct(lstProduct);
            adapter.notifyDataSetChanged();
        }
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

    private void actionRefresh(){
        binding.etSearch.setText("");
        binding.layoutNoResult.setVisibility(View.GONE);
        new SearchCarViewModel(getContext(),this,this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}