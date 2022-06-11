package com.devnweyi.carrental;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.devnweyi.carrental.databinding.ActivityBookingSuccessBinding;
import com.devnweyi.carrental.model.DriverModel;
import com.devnweyi.carrental.viewmodel.BookingSuccessViewModel;

public class BookingSuccessActivity extends AppCompatActivity implements BookingSuccessViewModel.EventListener {

    ActivityBookingSuccessBinding binding;
    DriverModel driverModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_booking_success);
        Intent i = getIntent();
        driverModel = i.getParcelableExtra("DriverModel");
        binding.setViewModel(new BookingSuccessViewModel(driverModel,this));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
    public void onCallHimClicked(String phoneNumber){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber ));
        startActivity(i);
    }

    @Override
    public void onHomeClicked(){
        Intent i=new Intent(BookingSuccessActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onSeeBookingClicked(){

    }
}