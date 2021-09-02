package com.devnweyi.carrental;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.devnweyi.carrental.adapter.CategoryAdapter;
import com.devnweyi.carrental.adapter.ProductAdapter;
import com.devnweyi.carrental.adapter.ProductRatingAdapter;
import com.devnweyi.carrental.databinding.ActivityCarDetailBinding;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.model.ProductRatingModel;
import com.devnweyi.carrental.ui.searchcar.SearchCarViewModel;
import com.devnweyi.carrental.viewmodel.CarDetailViewModel;

import java.util.List;

public class CarDetailActivity extends AppCompatActivity implements CarDetailViewModel.DataListener {

    ActivityCarDetailBinding binding;
    ProductModel productModel;

    public static Intent newIntent(Context context, ProductModel productModel) {
        Intent intent = new Intent(context, CarDetailActivity.class);
        intent.putExtra("ProductModel", productModel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_car_detail);
        Intent i = getIntent();
        productModel = i.getParcelableExtra("ProductModel");
        binding.setViewModel(new CarDetailViewModel(this,productModel,this));
        setTitle("");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setupRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onProductRatingLoaded(List<ProductRatingModel> lstProductRating) {
        ProductRatingAdapter adapter = (ProductRatingAdapter) binding.rvRating.getAdapter();
        adapter.setProductRating(lstProductRating);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        ProductRatingAdapter productRatingAdapter = new ProductRatingAdapter();
        binding.rvRating.setAdapter(productRatingAdapter);
        binding.rvRating.setLayoutManager(new LinearLayoutManager(this));
    }
}