package com.devnweyi.carrental.general;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;

import com.devnweyi.carrental.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SystemSetting {

    public final String DATE_FORMAT="MMMM dd";
    public final String TIME_FORMAT="hh:mm a";
    public DecimalFormat df= new DecimalFormat("#,###");
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String UserID = "userIdKey";
    public static final String UserName = "userNameKey";
    public static final String PhoneNumber = "phoneNumberKey";
    public static final String Password = "passwordKey";

    public String getTodayDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String date = dateFormat.format(Calendar.getInstance().getTime());
        return date.trim();
    }

    public String getCurrentTime(){
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        String time = timeFormat.format(Calendar.getInstance().getTime());
        return time.trim();
    }

    public static int getDaysDifference(Date pickUpDate, Date dropOffDate) {
        if(pickUpDate==null||dropOffDate==null) return 0;

        return (int)( (dropOffDate.getTime() - pickUpDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int checkValidPickUpDropOffDate(String strPickUpDate,String strDropOffDate){
        int dayDiff=0;
        try{
            SimpleDateFormat dateFormat=new SimpleDateFormat(DATE_FORMAT);
            Date pickUpDate = dateFormat.parse(strPickUpDate);
            Date dropOffDate = dateFormat.parse(strDropOffDate);
            dayDiff = getDaysDifference(pickUpDate,dropOffDate)+1;
            if(dayDiff <= 0)return dayDiff;
        } catch (ParseException ex){

        }
        return dayDiff;
    }

    public String getDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());  // 3 letter name form of the day
    }

    /** CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT */
    public boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
            //Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    public void showSnackBar(View view1) {
        Snackbar snackbar = Snackbar.make(view1, "No Internet Connection!", Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView textView = view.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.GREEN);
        snackbar.show();
    }
}
