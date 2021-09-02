package com.devnweyi.carrental;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.devnweyi.carrental.databinding.ActivityBookingBinding;
import com.devnweyi.carrental.general.SystemSetting;
import com.devnweyi.carrental.general.TimePickerDialog;
import com.devnweyi.carrental.model.BookingModel;
import com.devnweyi.carrental.model.ProductModel;
import com.devnweyi.carrental.viewmodel.BookingViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingActivity extends AppCompatActivity implements BookingViewModel.DataListener, TimePickerDialog.DataListener {

    ActivityBookingBinding binding;
    BookingModel bookingModel=new BookingModel();
    ProductModel productModel;
    SystemSetting systemSetting=new SystemSetting();
    private final int PICKUP_DATE_PICKER =1,DROPOFF_DATE_PICKER=2;
    static boolean isPickUpLocation;
    int dayDiff;

    public static Intent newIntent(Context context, ProductModel productModel) {
        Intent intent = new Intent(context, BookingActivity.class);
        intent.putExtra("productModel",productModel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_booking);
        Intent i=getIntent();
        productModel=i.getParcelableExtra("productModel");
        bookingModel.setProductId(productModel.getProductID());
        bookingModel.setProductName(productModel.getProductName());
        bookingModel.setPricePerDay(productModel.getPricePerDay());
        bookingModel.setPickUpDate(systemSetting.getTodayDate());
        bookingModel.setPickUpTime(systemSetting.getCurrentTime());
        bookingModel.setPickUpDay(systemSetting.getDayOfWeek());
        bookingModel.setDropOffDate(systemSetting.getTodayDate());
        bookingModel.setDropOffTime(systemSetting.getCurrentTime());
        bookingModel.setDropOffDay(systemSetting.getDayOfWeek());
        bookingModel.setRentalDays(1+getResources().getString(R.string.space)+getResources().getString(R.string.days));
        bookingModel.setTotalAmount(productModel.getPricePerDay()+getResources().getString(R.string.space)+getResources().getString(R.string.currency_mmk));
        binding.setViewModel(new BookingViewModel(this,bookingModel,this));
        binding.executePendingBindings();
        new TimePickerDialog(this);
        setTitle("");
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
    public void onResume(){
        super.onResume();
        if(isPickUpLocation)binding.etPickUpLocation.setText(MapsActivity.selectedLocation);
        else binding.etDropOffLocation.setText(MapsActivity.selectedLocation);
    }

    @BindingAdapter({"tripPlaceMessage"})
    public static void setTripPlaceError(EditText editText,String message){
        if(message!=null){
            editText.setError(message);
        }
    }

    @BindingAdapter({"totalPassengerMessage"})
    public static void setTotalPassengerError(EditText editText,String message){
        if(message!=null){
            editText.setError(message);
        }
    }

    @BindingAdapter({"pickUpLocationMessage"})
    public static void setPickUpLocationError(EditText editText,String message){
        if(message!=null){
            editText.setError(message);
        }
    }

    @BindingAdapter({"dropOffLocationMessage"})
    public static void setDropOffLocationError(EditText editText,String message){
        if(message!=null){
            editText.setError(message);
        }
    }

    @BindingAdapter({"invalidDateMessage"})
    public static void setInvalidDateError(View view, String message){
        if(message!=null){
            Toast.makeText(view.getContext(),message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPickUpDateClicked(){
        showDialog(PICKUP_DATE_PICKER);
    }

    @Override
    public void onDropOffDateClicked(){
        showDialog(DROPOFF_DATE_PICKER);
    }

    @Override
    public void onMapLoaded(boolean result){
        isPickUpLocation=result;
        Intent i=new Intent(BookingActivity.this,MapsActivity.class);
        startActivity(i);
    }

    @Override
    public void onSetPickUpTime(String time){
        binding.tvPickUpTime.setText(time);
    }

    @Override
    public void onSetDropOffTime(String time){
        binding.tvDropOffTime.setText(time);
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch(id){
            case PICKUP_DATE_PICKER:
                return showDatePicker(PICKUP_DATE_PICKER);
            case DROPOFF_DATE_PICKER:
                return showDatePicker(DROPOFF_DATE_PICKER);
        }
        return super.onCreateDialog(id);
    }

    private DatePickerDialog showDatePicker(int datePickerType){
        final Calendar cCalendar=Calendar.getInstance();
        DatePickerDialog datePicker=new DatePickerDialog(BookingActivity.this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cCalendar.set(Calendar.YEAR,year);
                cCalendar.set(Calendar.MONTH, monthOfYear);
                cCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                int day=cCalendar.get(Calendar.DAY_OF_WEEK);
                String dayOfWeek="";
                switch(day){
                    case Calendar.SUNDAY:
                        dayOfWeek="Sun";
                        break;
                    case Calendar.MONDAY:
                        dayOfWeek="Mon";
                        break;
                    case Calendar.TUESDAY:
                        dayOfWeek="Tue";
                        break;
                    case Calendar.WEDNESDAY:
                        dayOfWeek="Wed";
                        break;
                    case Calendar.THURSDAY:
                        dayOfWeek="Thu";
                        break;
                    case Calendar.FRIDAY:
                        dayOfWeek="Fri";
                        break;
                    case Calendar.SATURDAY:
                        dayOfWeek="Sat";
                        break;
                }

                SimpleDateFormat dateFormat=new SimpleDateFormat(systemSetting.DATE_FORMAT);
                if(datePickerType==PICKUP_DATE_PICKER) {
                    binding.tvPickUpDate.setText(dateFormat.format(cCalendar.getTime()));
                    binding.tvPickUpDay.setText(dayOfWeek);
                    dayDiff=systemSetting.checkValidPickUpDropOffDate(binding.tvPickUpDate.getText().toString(),binding.tvDropOffDate.getText().toString());
                    if(dayDiff > 0){
                        setRentalDayAndAmount();
                    }
                }
                else if(datePickerType==DROPOFF_DATE_PICKER) {
                    dayDiff=systemSetting.checkValidPickUpDropOffDate(binding.tvPickUpDate.getText().toString(),dateFormat.format(cCalendar.getTime()));
                    if(dayDiff > 0) {
                        binding.tvDropOffDate.setText(dateFormat.format(cCalendar.getTime()));
                        binding.tvDropOffDay.setText(dayOfWeek);
                        setRentalDayAndAmount();
                    }else{
                        Toast.makeText(getApplicationContext(),getResources().getText(R.string.booking_date_invalid),Toast.LENGTH_LONG).show();
                    }
                }
            }
        },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        return datePicker;
    }

    private void setRentalDayAndAmount(){
        binding.tvRentalDay.setText(dayDiff + getResources().getString(R.string.space) + getResources().getString(R.string.days));
        String[] prices=productModel.getPricePerDay().split(",");
        int length=prices.length;
        String strPricePerDay="";
        double pricePerDay=0;
        for(int i=0;i<length;i++){
            strPricePerDay+=prices[i];
        }
        if(strPricePerDay.length()!=0)pricePerDay=Double.parseDouble(strPricePerDay);
        double amount=pricePerDay*dayDiff;
        binding.tvAmount.setText(systemSetting.df.format(amount) + getResources().getString(R.string.space) + getResources().getString(R.string.currency_mmk));
    }

}