package com.devnweyi.carrental;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.devnweyi.carrental.adapter.PassengerFilterAdapter;
import com.devnweyi.carrental.adapter.PriceFilterAdapter;
import com.devnweyi.carrental.databinding.ActivityFilterBinding;
import com.devnweyi.carrental.model.PassengerFilterModel;
import com.devnweyi.carrental.model.PriceFilterModel;
import com.devnweyi.carrental.viewmodel.FilterViewModel;

import java.util.List;

public class FilterActivity extends AppCompatActivity implements FilterViewModel.DataListener{

    ActivityFilterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
        binding.setViewModel(new FilterViewModel(this, this));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
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

    private void setupRecyclerView() {
        PriceFilterAdapter priceFilterAdapter = new PriceFilterAdapter();
        binding.rvPrice.setAdapter(priceFilterAdapter);
        binding.rvPrice.setLayoutManager(new LinearLayoutManager(this));

        PassengerFilterAdapter passengerFilterAdapter = new PassengerFilterAdapter();
        binding.rvPassenger.setAdapter(passengerFilterAdapter);
        binding.rvPassenger.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPriceFilterLoaded(List<PriceFilterModel> lstPriceFilter) {
        PriceFilterAdapter adapter = (PriceFilterAdapter) binding.rvPrice.getAdapter();
        adapter.setPriceFilter(lstPriceFilter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPassengerFilterLoaded(List<PassengerFilterModel> lstPassengerFilter) {
        PassengerFilterAdapter adapter=(PassengerFilterAdapter) binding.rvPassenger.getAdapter();
        adapter.setPassengerFilter(lstPassengerFilter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFinished() {
        finish();
    }
}