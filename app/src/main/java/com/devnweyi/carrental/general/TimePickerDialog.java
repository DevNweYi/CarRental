package com.devnweyi.carrental.general;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.devnweyi.carrental.R;
import com.devnweyi.carrental.databinding.DialogTimePickerBinding;
import com.devnweyi.carrental.viewmodel.BookingViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

public class TimePickerDialog extends DialogFragment implements BookingViewModel.TimeListener {

    /*private static final String KEY_MY_INFO = "KEY_MY_INFO";
    private String myInfo;*/
    private static Context context;
    DialogTimePickerBinding binding;
    private static TimePickerDialog dialog;
    static DataListener dataListener;
    android.app.AlertDialog showDialog;

    public TimePickerDialog(){ }

    public TimePickerDialog(DataListener dataListener1){
        this.dataListener=dataListener1;
    }

    public static TimePickerDialog newInstance(String myInfo, Context context1) {
        dialog = new TimePickerDialog();
        context=context1;
        /*Bundle bundle = new Bundle();
        bundle.putString(KEY_MY_INFO, myInfo);
        dialog.setArguments(bundle);*/
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //myInfo = getArguments().getString(KEY_MY_INFO);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_time_picker, null,false);
        binding.setViewModel(new BookingViewModel(this));
        binding.timePicker.setIs24HourView(false);

        //binding.setMyInfo(myInfo);

        android.app.AlertDialog.Builder dialog=new android.app.AlertDialog.Builder(context);
        dialog.setView(binding.getRoot());

        dialog.setCancelable(true);
        showDialog=dialog.create();

        return showDialog;
    }

    @Override
    public void onPickUpTimeClicked(){
        if(dataListener!=null)dataListener.onSetPickUpTime(getSelectedTime());
        showDialog.dismiss();
    }

    @Override
    public void onDropOffTimeClicked(){
        if(dataListener!=null)dataListener.onSetDropOffTime(getSelectedTime());
        showDialog.dismiss();
    }

    @Override
    public void onCloseTimePicker(){
        showDialog.dismiss();
    }

    private String getSelectedTime(){
        String AM_PM,strHour,strMinute;
        int hour =binding.timePicker.getCurrentHour();
        int minute =binding.timePicker.getCurrentMinute();
        if(hour < 12) {
            AM_PM = "AM";
        } else {
            AM_PM = "PM";
        }
        strHour=String.valueOf(hour);
        if(strHour.length()==1)strHour="0"+strHour;
        strMinute=String.valueOf(minute);
        if(strMinute.length()==1)strMinute="0"+strMinute;

        return strHour +":" + strMinute + " " + AM_PM;
    }

    public interface DataListener{
        void onSetPickUpTime(String time);
        void onSetDropOffTime(String time);
    }
}
